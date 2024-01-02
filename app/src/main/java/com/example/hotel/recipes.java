package com.example.hotel;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class recipes extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        TextView tv = findViewById(R.id.categoryTextView);

        String url = "https://www.themealdb.com/api/json/v1/1/categories.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<category> categories = parseCategories(response);

                for (category category : categories) {
                    tv.append(category.getStrCategory() + "\n");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tv.setText("error cant get catigories");
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    private List<category> parseCategories(JSONArray jsonArray) {
        List<category> categories = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonCategory = jsonArray.getJSONObject(i);

                category category = new category();
                category.setIdCategory(jsonCategory.getString("idCategory"));
                category.setStrCategory(jsonCategory.getString("strCategory"));
                category.setStrCategoryThumb(jsonCategory.getString("strCategoryThumb"));
                category.setStrCategoryDescription(jsonCategory.getString("strCategoryDescription"));

                categories.add(category);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return categories;
    }
}
