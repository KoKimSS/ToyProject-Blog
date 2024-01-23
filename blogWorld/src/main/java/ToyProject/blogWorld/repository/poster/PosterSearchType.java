package ToyProject.blogWorld.repository.poster;

public enum PosterSearchType {
    TITLE,CONTENTS,MIX;
    public static PosterSearchType toType(String stringParam){
        return switch(stringParam){
            case "TITLE" ->  TITLE;
            case "CONTENTS" ->  CONTENTS;
            default -> MIX;
        };
    }
}
