package ToyProject.blogWorld.repository.poster;


public enum PosterSortType {
    ACCURACY,LATEST,POPULAR,MIX;

    public static PosterSortType toType(String stringParam){
        return switch(stringParam){
            case "ACCURACY" ->  ACCURACY;
            case "LATEST" ->  LATEST;
            case "POPULAR" ->  POPULAR;
            default -> MIX;
        };
    }
}
