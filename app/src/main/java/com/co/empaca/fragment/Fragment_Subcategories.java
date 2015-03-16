package com.co.empaca.fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.co.empaca.R;
import com.co.empaca.adapters.CategoriesAdapter;
import com.co.empaca.adapters.SubcategoriesAdapter;
import com.co.empaca.models.Products;
import com.co.empaca.utils.Constants;
import com.co.empaca.utils.GetProducts;

import java.util.ArrayList;


public class Fragment_Subcategories extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private RecyclerView subcategoryList;
    private SubcategoriesAdapter subcategoriesAdapter;

    private Integer categoria;
    private String mParam1;

    public static Fragment_Subcategories newInstance(String param1) {
        Fragment_Subcategories fragment = new Fragment_Subcategories();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public Fragment_Subcategories() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            categoria = getArguments().getInt("categoria");

            //de la lista de categorias solo obtengo la posicion al hacer clic,
            //lo mejor seria capturar el ID de la categoria, para consultar el
            //api de forma satisfactoria
            categoria += 1;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.list_subcategories, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        subcategoryList = (RecyclerView) rootView.findViewById(R.id.listSubcategories);
        subcategoryList.setHasFixedSize(true);
        subcategoryList.setLayoutManager(linearLayoutManager);

        //se ejecuta el llamado al AsyncTask para llenar el adaptador del Grid
        new ApiRestConsumer().execute();

        return rootView;
    }

    //se llena el adaptador de la lista de categorias, de acuerdo al
    //Array Products que arrojó el post del Asynctask
    public void updateList(ArrayList<Products> ArrayProducts){

        subcategoriesAdapter = new SubcategoriesAdapter(ArrayProducts);
        subcategoryList.setAdapter(subcategoriesAdapter);
        subcategoriesAdapter.notifyDataSetChanged();

    }

    //clase para obtener la cadena JSon del APIRest
    class ApiRestConsumer extends AsyncTask<Object, Void, ArrayList<Products>> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage(getResources().getString(R.string.load_info_sub));
            progressDialog.show();
        }


        @Override
        protected ArrayList<Products> doInBackground(Object... param) {
            return GetProducts.GetProducts(Constants.URL_API_SUBCATEGORIAS + "?categoria=" + categoria , GetProducts.SUBCATEGORIES);
        }

        @Override
        protected void onPostExecute(ArrayList<Products> ArrayProducts){
            progressDialog.dismiss();

            if (ArrayProducts.isEmpty()){
                Toast.makeText(getActivity(), getResources().getString(R.string.error_load),
                        Toast.LENGTH_LONG).show();
            }else {
                updateList(ArrayProducts);
            }

        }

    }

}
