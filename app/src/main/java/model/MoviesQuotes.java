package model;

public class MoviesQuotes {


    private String quoteTxt;
    private String quoteCharacterImage;
    private String writerName;
    private String profileImage;


    public String getQuoteTxt() {
        return quoteTxt;
    }

    public void setQuoteTxt(String quoteTxt) {
        this.quoteTxt = quoteTxt;
    }

    public String getQuoteCharacterImage() {
        return quoteCharacterImage;
    }

    public void setQuoteCharacterImage(String quoteCharacterImage) {
        this.quoteCharacterImage = quoteCharacterImage;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }


    @Override
    public String toString() {
        return "MoviesQuotes{" +
                "quoteTxt='" + quoteTxt + '\'' +
                ", quoteCharacterImage='" + quoteCharacterImage + '\'' +
                ", writerName='" + writerName + '\'' +
                ", profileImage='" + profileImage + '\'' +
                '}';
    }

}
