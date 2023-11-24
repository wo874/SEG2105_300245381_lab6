package com.example.lab6demo;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView idView;
    EditText productBox;
    EditText skuBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idView = (TextView) findViewById(R.id.productID);
        productBox = (EditText) findViewById(R.id.productName);
        skuBox = (EditText) findViewById(R.id.productSku);
        dbHandler = new MyDBHandler(this);
    }

    public void newProduct (View view) {

        int sku = Integer.parseInt(skuBox.getText().toString());

        Product product= new Product(productBox.getText().toString(), sku);

        dbHandler.addProduct(product);

        productBox.setText("");

        skuBox.setText("");

    }


    public void lookupProduct (View view) {

        String productName=productBox.getText().toString();
        Product product=dbHandler.findProduct(productName);

        if (product != null) {
            idView.setText(String.valueOf(product.getID()));
            skuBox.setText(String.valueOf(product.getSku()));
        } else {
            idView.setText("No Match Found");
        }
    }


    public void removeProduct (View view) {

        String productName=productBox.getText().toString();
        boolean result=dbHandler.deleteProduct(productName);

        if (result) {
            idView.setText("Record Deleted");
            productBox.setText("");
            skuBox.setText("");
        }
        else
            idView.setText("No Match Found");
    }

    public void about(View view) {
        Intent aboutIntent = new Intent(this, About.class);
        startActivity(aboutIntent);
    }
}
