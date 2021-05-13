package com.example.nutritiontrackerguiv4.ui.recipes;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.net.*;
import java.util.List;

import com.example.nutritiontrackerguiv4.R;
import com.example.nutritiontrackerguiv4.database.Allergies;
import com.example.nutritiontrackerguiv4.database.AllergiesDAO;
import com.example.nutritiontrackerguiv4.database.NutritionDatabase;

import org.apache.poi.ss.usermodel.Table;

public class RecipesFragment extends Fragment {

    private RecipesViewModel mViewModel;
    View root;
    private NutritionDatabase db;

    public static RecipesFragment newInstance() {
        return new RecipesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.recipes_fragment, container, false);


        root.findViewById(R.id.veganButton).setOnClickListener(view -> clicked_btn("https://simple-veganista.com/"));

        root.findViewById(R.id.vegetarianButton).setOnClickListener(view -> clicked_btn("https://themodernproper.com/60-best-vegetarian-meals"));

        root.findViewById(R.id.ketoButton).setOnClickListener(view -> clicked_btn("https://www.delish.com/keto-recipes/"));

        root.findViewById(R.id.glutenFreeButton).setOnClickListener(view -> clicked_btn("https://www.allrecipes.com/recipes/741/healthy-recipes/gluten-free/"));

        root.findViewById(R.id.lowFatButton).setOnClickListener(view -> clicked_btn("https://www.allrecipes.com/recipes/1231/healthy-recipes/low-fat/"));

        root.findViewById(R.id.highProteinButton).setOnClickListener(view -> clicked_btn("https://www.skinnytaste.com/high-protein/"));

        root.findViewById(R.id.mindButton).setOnClickListener(view -> clicked_btn("https://www.pinterest.com/thespicyrd/mediterranean-diet-mind-diet-recipes/"));

        root.findViewById(R.id.highFiberButton).setOnClickListener(view -> clicked_btn("https://www.foodnetwork.com/topics/high-fiber-recipes"));

        root.findViewById(R.id.lowCostButton).setOnClickListener(view -> clicked_btn("https://www.budgetbytes.com/"));

        root.findViewById(R.id.lowEffortButton).setOnClickListener(view -> clicked_btn("https://www.taste.com.au/quick-easy/galleries/low-cook-dinners-busy-weeknights/hyu3sf13"));

        TableLayout linkTable = root.findViewById(R.id.linkTable);

        db = NutritionDatabase.getDatabase(getContext());
        Allergies allergies = db.getAllergiesDAO().getAllAllergies().get(0);

        System.out.println("Are there nut allergies? "+ allergies.getNuts());

        if (allergies.getNuts() || allergies.getSeafood() || allergies.getMilk() || allergies.getEggs() || allergies.getShellfish() || allergies.getSoybeans() || allergies.getWheat())
        {
            TableRow tr = new TableRow(getContext());
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            if (allergies.getNuts()){
                Button b1 = new Button(getContext());
                b1.setText("Nut Free");
                b1.setOnClickListener(view -> clicked_btn("https://www.eatingwell.com/recipes/18051/dietary-restrictions/nut-free/"));
                b1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                tr.addView(b1);
            }
            if (allergies.getSeafood()){
                Button b2 = new Button(getContext());
                b2.setText("Seafood Free");
                b2.setOnClickListener(view -> clicked_btn("https://www.spokin.com/allergy-friendly-recipes/tag/Shellfish+Free"));
                b2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                tr.addView(b2);
            }
            if (allergies.getEggs()){
                Button b3 = new Button(getContext());
                b3.setText("Egg Free");
                b3.setOnClickListener(view -> clicked_btn("https://www.skinnytaste.com/egg-free-recipes/"));
                b3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                tr.addView(b3);
            }
            linkTable.addView(tr, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        }


        return root;
    }

    public void clicked_btn(String url)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RecipesViewModel.class);
        // TODO: Use the ViewModel
    }

}