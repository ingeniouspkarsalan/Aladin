package ustaad.aladin.com.classes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONParser {

    static List<layout_category_class> categoryClassList;

    public static List<layout_category_class> parse_category(String content)
    {
        JSONArray jsonArray = null;
        layout_category_class layout_category_class = null;
        try
        {
            jsonArray = new JSONArray(content);
            categoryClassList = new ArrayList<>();
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                layout_category_class = new layout_category_class();
                layout_category_class.setCategory_id(jsonObject.getString("c_id"));
                layout_category_class.setCategory_name(jsonObject.getString("c_name"));
                layout_category_class.setCategory_image(jsonObject.getString("c_icon"));
                layout_category_class.setIs_product_or_service(jsonObject.getString("p_or_s"));
                layout_category_class.setParrent(jsonObject.getString("parrent"));



                categoryClassList.add(layout_category_class);
            }
            return categoryClassList;
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        }

    }
}
