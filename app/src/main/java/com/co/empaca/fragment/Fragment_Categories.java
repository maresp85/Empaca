package com.co.empaca.fragment;

import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.co.empaca.Activity_second;
import com.co.empaca.adapters.CategoriesAdapter.ClickListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.co.empaca.R;
import com.co.empaca.adapters.CategoriesAdapter;
import com.co.empaca.models.Products;
import com.co.empaca.utils.Constants;
import com.co.empaca.utils.GetProducts;

import java.util.ArrayList;
import java.util.InputMismatchException;


public class Fragment_Categories extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private RecyclerView categoryList;
    private CategoriesAdapter categoriesAdapter;
    private ArrayList<Products> ArrayProducts;

    private String mParam1;


    public static Fragment_Categories newInstance(String param1) {
        Fragment_Categories fragment = new Fragment_Categories();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public Fragment_Categories() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflar el Layout para este fragmento
        View rootView = inflater.inflate(R.layout.list_categories, container, false);

        // Recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        categoryList = (RecyclerView) rootView.findViewById(R.id.listCategories);
        categoryList.setHasFixedSize(true);
        categoryList.setLayoutManager(linearLayoutManager);

        //se ejecuta el llamado al AsyncTask para llenar el adaptador del Grid
        new ApiRestConsumer().execute();

        return rootView;
    }

    //se llena el adaptador de la lista de categorias, de acuerdo al
    //Array Products que arrojó el post del Asynctask
    public void updateList(ArrayList<Products> ArrayProducts){

        categoriesAdapter = new CategoriesAdapter(ArrayProducts);

        //Listener para los eventos clic
        categoriesAdapter.setClickListener(new ClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //se llama la actividad donde se muestra la subcategoria
                Intent intent = new Intent(getActivity(), Activity_second.class);
                intent.putExtra("categoria", position);
                startActivity(intent);
            }
        });
        categoryList.setAdapter(categoriesAdapter);
        categoriesAdapter.notifyDataSetChanged();

    }

    //clase para obtener la cadena JSon del APIRest
    class ApiRestConsumer extends AsyncTask<Object, Void, ArrayList<Products>> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage(getResources().getString(R.string.load_info));
            progressDialog.show();
        }


        @Override
        protected ArrayList<Products> doInBackground(Object... param) {
            return GetProducts.GetProducts(Constants.URL_API_CATEGORIAS, GetProducts.CATEGORIES);
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
