package itbrains.az.blog.helpers;

public class SeoHelper {

    public String seoUrlHelper(String text){
        // ["bavariya", "klubundan", "gözlənilməz", "qerar"]
        // "bavariya"-klubundan-gözlənilməz-qerar

        String[] change = text.toLowerCase()
                .replaceAll("ə", "e")
                .replaceAll("ç", "c")
                .replaceAll("ö", "o")
                .replaceAll("ö", "o")
                .split(" ");
        String result = String.join("-", change);
        return result.replaceAll("[^a-z0-9-]","");
    }
}
