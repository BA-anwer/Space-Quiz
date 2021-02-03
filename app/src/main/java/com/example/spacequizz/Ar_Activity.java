package com.example.spacequizz;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.MaterialFactory;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ShapeFactory;
import com.google.ar.sceneform.rendering.Texture;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class Ar_Activity extends AppCompatActivity {
     ArFragment arFragment;
     ModelRenderable materialTexture;
     MediaPlayer media ;
     int texture_planete ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar_);
        arFragment = (ArFragment)getSupportFragmentManager().findFragmentById(R.id.ux_fragment);

        //Music
        media=MediaPlayer.create(Ar_Activity.this,R.raw.music_espace);
        media.start();
        media.setLooping(true);


        //Texture
         String pl_name = getIntent().getStringExtra("planete_name");
        switch (pl_name){
            case "la Terre": {
                texture_planete = R.drawable.earth_texture ;
                break;}

            case "Mercure": {
                texture_planete = R.drawable.mercure_texture ;
                break;}

            case "Vénus": {
                texture_planete = R.drawable.venus_texture ;
                break;}

            case "Mars": {
                texture_planete = R.drawable.mars_texture ;
                break;}

            case "Jupiter": {
                texture_planete = R.drawable.jupiter_texture ;
                break;}

            case "Saturne": {
                texture_planete = R.drawable.saturune_texture ;
                break;}

            case "Uranus": {
                texture_planete = R.drawable.uranus_texture ;
                break;}

            case "Neptune": {
                texture_planete = R.drawable.neptune_texture ;
                break;}

            case "Pluton": {
                texture_planete = R.drawable.pluton_texture ;
                break;}

        }







        Texture.builder()
                .setSource(this, texture_planete)
                .build()
                .thenAccept (texture -> MaterialFactory.makeOpaqueWithTexture(this, texture)
                        .thenAccept(
                                material-> {
                                    materialTexture = ShapeFactory.makeSphere(0.1f, new Vector3(0f, 1f, 0f),material);
                                }));


        arFragment.setOnTapArPlaneListener(
                (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
                    if ( materialTexture == null) {
                        return;
                    }
                    if (plane.getType() != Plane.Type.HORIZONTAL_UPWARD_FACING) {
                        return;
                    }


                    //On créé le point d'encrage du modèle 3d
                    Anchor anchor = hitResult.createAnchor();
                    AnchorNode anchorNode = new AnchorNode(anchor);
                    anchorNode.setParent(arFragment.getArSceneView().getScene());

                    //On attache ensuite notre modèle au point d'encrage
                    TransformableNode Node = new TransformableNode(arFragment.getTransformationSystem());
                    Node.setParent(anchorNode);
                    Node.setRenderable(materialTexture);
                    Node.select();



                }
        );
    }

    @Override
    protected void onPause() {
        super.onPause();
        media.release();
    }
}