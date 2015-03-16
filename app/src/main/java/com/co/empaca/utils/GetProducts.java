package com.co.empaca.utils;

import android.util.Log;
import com.co.empaca.models.Products;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


public class GetProducts {

    public static final String TAG = "DEBUG";
    private static JSONArray jsonArray = null;
    private static final String TAG_PRODUCTS = "products";

    public final static int CATEGORIES = 1;
    public final static int SUBCATEGORIES = 2;

    public static ArrayList<Products> GetProducts(String url_api, int tipo){

        GetJSon jSon = new GetJSon();
        String jSonString = jSon.makeServiceCall(url_api, GetJSon.GET);

        ArrayList<Products> ArrayProducts = new ArrayList<Products>();

        if (jSonString == "ERROR"){
            return ArrayProducts;
        }

        if (jSonString != null){

            try {
                jsonArray = new JSONArray(jSonString);


                for (int i = 0; i < jsonArray.length(); i++){

                    Products products = new Products();
                    products.setName(jsonArray.getJSONObject(i).getString("nombre"));
                    products.setImagen(jsonArray.getJSONObject(i).getString("imagen"));

                    if (tipo == SUBCATEGORIES) {
                        products.setDescription(jsonArray.getJSONObject(i).getString("descripcion"));
                    }

                    ArrayProducts.add(i, products);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }else{
            Log.e(TAG, "Respuesta JSon nula");
        }

        return ArrayProducts;
    }
}
