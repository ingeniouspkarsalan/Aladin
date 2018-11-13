package ustaad.aladin.com.classes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONParser {

    static List<layout_category_class> categoryClassList;
    static ArrayList<bus_list_class> bus_list_classList;

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

    public static ArrayList<bus_list_class> parse_bus_list(String content)
    {
        JSONArray jsonArray = null;
        bus_list_class bus_list_class = null;
        try
        {
            jsonArray = new JSONArray(content);
            bus_list_classList = new ArrayList<>();
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                bus_list_class = new bus_list_class(jsonObject.getString("b_id"),jsonObject.getString("b_name"),jsonObject.getString("b_image"),
                        jsonObject.getString("b_mobile"),jsonObject.getString("b_city"),jsonObject.getLong("b_lat"),jsonObject.getLong("b_long"));
                bus_list_classList.add(bus_list_class);
            }
            return bus_list_classList;
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        }

    }
}
