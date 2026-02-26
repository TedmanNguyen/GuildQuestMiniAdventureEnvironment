public class DefaultCampaign extends Campaign implements CampaignFactory {
    DefaultCampaign(int id,int userId,String name){
        super(id,userId,name);
    }

    @Override
    public Campaign createCampaign(int id, int userId, String name){
        System.out.println("Campaign "+ name + " was created");
        return new DefaultCampaign(id,userId,name);
    }
}