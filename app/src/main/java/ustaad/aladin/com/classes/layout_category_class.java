package ustaad.aladin.com.classes;

import android.graphics.drawable.Drawable;

public class layout_category_class {
    private Drawable image_id;
    private String category_text;

    public layout_category_class(Drawable image_id, String category_text) {
        this.image_id = image_id;
        this.category_text = category_text;
    }

    public Drawable getImage_id() {
        return image_id;
    }



    public String getCategory_text() {
        return category_text;
    }


}
