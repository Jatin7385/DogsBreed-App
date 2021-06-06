package com.example.dogbreedapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView title;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    String names[],url[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        names = new String[]{"affenpinscher", "african", "airedale", "akita", "appenzeller", "australian", "basenji", "beagle", "bluetick", "borzoi", "bouvier", "boxer", "brabancon", "briard", "buhund", "bulldog", "bullterrier", "cairn", "cattledog", "chihuahua", "chow", "clumber", "cockapoo", "collie", "coonhound", "corgi", "cotondetulear", "dachshund", "dalmatian", "dane", "deerhound", "dhole", "dingo", "doberman", "elkhound", "entlebucher", "eskimo", "finnish", "frise", "germanshepherd", "greyhound", "groenendael", "havanese", "hound", "husky", "keeshond", "kelpie", "komondor", "kuvasz", "labradoodle", "labrador", "leonberg", "lhasa", "malamute", "malinois", "maltese", "mastiff", "mexicanhairless", "mix", "mountain", "newfoundland", "otterhound", "ovcharka", "papillon", "pekinese", "pembroke", "pinscher", "pitbull", "pointer", "pomeranian", "poodle", "pug", "puggle", "pyrenees", "redbone", "retriever", "ridgeback", "rottweiler", "saluki", "samoyed", "schipperke", "schnauzer", "setter", "sheepdog", "shiba", "shihtzu", "spaniel", "springer", "stbernard", "terrier", "vizsla", "waterdog", "weimaraner", "whippet", "wolfhound"};
        recyclerView = findViewById(R.id.recylerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DogApi dogApi = retrofit.create(DogApi.class);

        ImageApi imageApi = retrofit.create(ImageApi.class);
        Call<Images> call;
        for(int i=0;i<names.length;i++) {
            //System.out.println(names[i]);
            call = imageApi.getImage(names[i]);
            int finalI = i;
            //System.out.println(finalI);

            //Nothing after this line gets printed
            call.enqueue(new Callback<Images>() {
                @Override
                public void onResponse(Call<Images> call, Response<Images> response) {
                    //System.out.println("Hello");
                    if (!response.isSuccessful()) {
                        System.out.println("Code: " + response.code());
                        return;
                    }
                    Images images = response.body();
                    url[finalI] = images.getUrl();
                    System.out.println("https://dog.ceo/api/breed/"+names[finalI]+"/images/random");
                }

                @Override
                public void onFailure(Call<Images> call, Throwable t) {
                    System.out.println("Error : " + t.toString());
                }
            });
        }

        /*Call<Root> call = dogApi.getRoot();
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (!response.isSuccessful()) {
                    title.setText("Code: " + response.code());
                    return;
                }
                Root roots = response.body();
                System.out.println(roots.getMessage());

            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                title.setText("Error : "+ t.toString());
            }
        });*/

        recyclerAdapter = new RecyclerAdapter(MainActivity.this,names,url);
        recyclerView.setAdapter(recyclerAdapter);

    }
}