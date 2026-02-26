package GuildQuest;

import java.util.*;

public class CampaignManager {
    public List<Campaign> campaigns = new ArrayList<>();

    // UML: owners:Dict(int:User), collaborators:Dict(int:User[]), member:Dict(int:User[])
    public Map<Integer, User> owners = new HashMap<>();
    public Map<Integer, Set<User>> collaborators = new HashMap<>();
    public Map<Integer, Set<User>> member = new HashMap<>();

    public void addCampaign(Campaign campaign) {
        campaigns.add(campaign);
    }

    public boolean removeCampaign(int id) {
        Campaign c = findCampaign(id);
        if (c == null) return false;

        blastNotify(id, "Campaign " + c.name + " has been deleted.");

        campaigns.remove(c);
        owners.remove(id);
        collaborators.remove(id);
        member.remove(id);
        return true;
    }

    public boolean updateCampaign(int id, Campaign campaign) {
        Campaign c = findCampaign(id);
        if (c == null) return false;
        c.name = campaign.name;
        c.publicc = campaign.publicc;
        c.archived = campaign.archived;
        return true;
    }

    public void generateTimeline(int id) {
        // Minimal placeholder: could print quests ordered by start time
        Campaign c = findCampaign(id);
        if (c == null) return;
        c.questEvents.sort(Comparator.comparingInt(q -> q.startTime));
    }

    // === A3 CHANGES START: Refactoring #2 (Replace Primitive/String with Enum) ===
    public Role getRole(User user, int campaignId) {
        if (user == null) return Role.NONE;

        User owner = owners.get(campaignId);
        // Prefer uid-based equality (submission-safe) but keep reference check too.
        if (isSameUser(owner, user)) return Role.OWNER;

        // Java 8-safe (no Set.of())
        if (collaborators.getOrDefault(campaignId, Collections.<User>emptySet()).contains(user)) {
            return Role.COLLABORATOR;
        }
        if (member.getOrDefault(campaignId, Collections.<User>emptySet()).contains(user)) {
            return Role.MEMBER;
        }
        return Role.NONE;
    }
    // === A3 CHANGES END: Refactoring #2 (Replace Primitive/String with Enum) ===



    // === A3 CHANGES START: AI-Assisted Refactoring (Move Method) ===
    // Moves access/permission logic out of Main into CampaignManager (UI layer should not enforce policy).
    public Campaign findCampaignById(int id) {
        // === A3 CHANGES: AI-Assisted Refactoring (Move Method) ===
        // Reuse existing finder so we don't duplicate campaign lookup logic.
        return findCampaign(id);
    }

    public boolean canViewCampaign(User user, int campaignId) {
        Campaign c = findCampaignById(campaignId);
        if (c == null) return false;

        // public campaigns are viewable by anyone
        if (c.publicc) return true;

        // private campaigns require a role
        Role role = getRole(user, campaignId);
        return role != Role.NONE;
    }

    public boolean canEditCampaign(User user, int campaignId) {
        Role role = getRole(user, campaignId);
        return role == Role.OWNER;
    }

    /**
     * Helper for UI code: returns the campaign if viewable, otherwise null.
     * Keeps menu code short and keeps permission rules centralized.
     */
    public Campaign getViewableCampaignOrNull(User user, int campaignId) {
        Campaign c = findCampaignById(campaignId);
        if (c == null) return null;
        return canViewCampaign(user, campaignId) ? c : null;
    }
    // === A3 CHANGES END: AI-Assisted Refactoring (Move Method) ===


    public void setVisibility(int id, boolean publicc) {
        Campaign c = findCampaign(id);
        if (c != null) c.publicc = publicc;
    }

    public void addUser(User user, int campaignId) {
        member.computeIfAbsent(campaignId, k -> new HashSet<>()).add(user);
        quietNotify(user, "You have been added to campaign " + campaignId + ".");
    }

    public void removeUser(User user, int campaignId) {
        Set<User> m = member.get(campaignId);
        if (m != null) m.remove(user);
        Set<User> col = collaborators.get(campaignId);
        if (col != null) col.remove(user);
    }

    /**
     * Kept for backwards compatibility with any existing callers.
     * A3 refactor introduced Role enum, so we provide a submission-safe bridge.
     * NOTE: The original UML is unclear about which campaign this applies to; so we keep it a no-op.
     */
    public void setRole(User user, String role) {
        // Minimal: role applies to the last campaign user touched is unclear in UML; keep as no-op
        // In a full app, you'd pass campaignId too.
        // Bridge parse exists so the enum refactor remains consistent and compilable.
        Role parsed = parseRole(role);
        // No-op (by design per your existing comment)
        // If you later decide to implement: you'd route to setRole(user, campaignId, parsed)
    }

    // === A3 CHANGES START: Refactoring #2 helper overload (does not remove old functionality) ===
    /**
     * Enum-friendly overload to align with Refactoring #2.
     * Still a no-op because campaignId is required to implement this correctly.
     */
    public void setRole(User user, Role role) {
        // No-op; see setRole(User,String) comment.
    }
    // === A3 CHANGES END: Refactoring #2 helper overload ===

    public List<Campaign> getVisibleCampaigns(User user) {
        List<Campaign> vis = new ArrayList<>();
        for (Campaign c : campaigns) {
            if (c.publicc) { vis.add(c); continue; }
            // === A3 CHANGES: Refactoring #2 (Replace Primitive/String with Enum) ===
            Role role = getRole(user, c.id);
            if (role != Role.NONE) vis.add(c);
        }
        return vis;
    }

    // === A3 CHANGES START: AI-Assisted Design Pattern (Observer Pattern) ===
    // NOTE: This is SEPARATE from "AI-Assisted Refactoring (Move Method)" above.
    // Pattern intent:
    //   - CampaignManager acts as the Subject (publisher of notifications)
    //   - Users act as Observers via NotificationListener/User.notify(...)
    // This replaces the previous empty placeholders with a real, decoupled notification mechanism.
    // It does NOT remove any prior behavior: notifications were previously no-ops, so adding them
    // only increases functionality without breaking existing flows.
    public void blastNotify(int campaignId, String message) {
        // Notify owner (if any)
        User owner = owners.get(campaignId);
        if (owner != null) {
            owner.notify(message);
        }

        // Notify collaborators
        for (User u : collaborators.getOrDefault(campaignId, Collections.<User>emptySet())) {
            if (u != null) u.notify(message);
        }

        // Notify members (includes owner/collaborators too, but that's OK; or we can de-dupe if desired)
        for (User u : member.getOrDefault(campaignId, Collections.<User>emptySet())) {
            if (u != null) u.notify(message);
        }
    }

    public void quietNotify(User user, String message) {
        if (user != null) {
            user.notify(message);
        }
    }
    // === A3 CHANGES END: AI-Assisted Design Pattern (Observer Pattern) ===

    public void setOwner(int campaignId, User owner) {
        owners.put(campaignId, owner);
        member.computeIfAbsent(campaignId, k -> new HashSet<>()).add(owner);
    }

    public void addCollaborator(int campaignId, User u) {
        collaborators.computeIfAbsent(campaignId, k -> new HashSet<>()).add(u);
        member.computeIfAbsent(campaignId, k -> new HashSet<>()).add(u);
        quietNotify(u, "You have been made a collaborator on campaign " + campaignId + ".");
    }

    private Campaign findCampaign(int id) {
        for (Campaign c : campaigns) if (c.id == id) return c;
        return null;
    }


    private boolean isSameUser(User a, User b) {
        if (a == null || b == null) return false;
        if (a == b) return true;
        return a.uid == b.uid;
    }

    /**
     * Parses legacy string roles into the new enum.
     * Accepts enum names ("OWNER") and labels ("owner"), plus common variants.
     */
    private Role parseRole(String role) {
        if (role == null) return Role.NONE;
        String r = role.trim().toLowerCase(Locale.ROOT);
        if (r.isEmpty()) return Role.NONE;

        if (r.equals("owner")) return Role.OWNER;
        if (r.equals("collaborator") || r.equals("collab")) return Role.COLLABORATOR;
        if (r.equals("member")) return Role.MEMBER;
        if (r.equals("none")) return Role.NONE;

        // Also accept enum-style inputs
        try {
            return Role.valueOf(role.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ex) {
            return Role.NONE;
        }
    }
}