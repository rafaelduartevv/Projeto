package com.example.projetofinal;

public class ImageData {

    private String description;
    private String image;

    public ImageData(){

    }

    public ImageData(String description, String image){
        this.description = description;
        this.image = image;

    }

    public String getDescription(){
        return description;
    }

    public String getImage(){
        return image;
    }

}
