package com.foodsoncampus.foodies.filters;

import android.widget.Filter;

import com.foodsoncampus.foodies.adapters.AdapterRestaurant;
import com.foodsoncampus.foodies.models.ModelCategory;

import java.util.ArrayList;

public class FilterCategory extends Filter {

    //arraylist in which we want to search
    ArrayList<ModelCategory> filterList;
    //adapter in which filter need to be implemented
    AdapterRestaurant adapterRestaurant;

    //constructor
    public FilterCategory(ArrayList<ModelCategory> filterList, AdapterRestaurant adapterRestaurant) {
        this.filterList = filterList;
        this.adapterRestaurant = adapterRestaurant;
    }


    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        //value should not be null and empty
        if (constraint != null && constraint.length() > 0){

            //change to upper case, or lower case to avoid case sensitivity
            constraint = constraint.toString().toUpperCase();
            ArrayList<ModelCategory> filteredModels = new ArrayList<>();

            for (int i=0; i<filterList.size(); i++){
                //validate
                if (filterList.get(i).getCategory().toUpperCase().contains(constraint)){
                    //add to filtered list
                    filteredModels.add(filterList.get(i));
                }
            }

            results.count = filteredModels.size();
            results.values = filteredModels;

        }
        else {
            results.count = filterList.size();
            results.values = filterList;
        }

        return results; //dont miss it
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        //apply fiter cahnges
        adapterRestaurant.categoryArrayList = (ArrayList<ModelCategory>)results.values;

        //notify cahnges
        adapterRestaurant.notifyDataSetChanged();
    }
}
