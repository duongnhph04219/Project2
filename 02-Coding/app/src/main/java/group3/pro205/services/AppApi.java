package group3.pro205.services;



public class AppApi extends BaseApi {
    private AppServices hmwService;

    public AppServices service() {
        if (hmwService == null) {
            hmwService = restAdapter.create(AppServices.class);
        }
        return hmwService;
    }
}
