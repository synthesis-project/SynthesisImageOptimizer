package com.synthesis.data.models.dataOContent;

import java.util.ArrayList;
import java.util.List;


public class Attributes {

    private String plant_name;
    private String s3_image_url;
    private String s3_masonry_image_url;
    private String water_image;
    private String placement_image;
    private String fertilization_image;
    private String propogation_image;

    public String getPropogation_image() {
        return propogation_image;
    }


    public void setPropogation_image(String propogation_image) {
        this.propogation_image = propogation_image;
    }


    public String getFertilization_image() {
        return fertilization_image;
    }


    public void setFertilization_image(String fertilization_image) {
        this.fertilization_image = fertilization_image;
    }


    public String getPlacement_image() {
        return placement_image;
    }


    public void setPlacement_image(String placement_image) {
        this.placement_image = placement_image;
    }


    public String getWater_image() {
        return water_image;
    }


    public void setWater_image(String water_image) {
        this.water_image = water_image;
    }


    public String getS3_masonry_image_url() {
        return s3_masonry_image_url;
    }


    public void setS3_masonry_image_url(String s3_masonry_image_url) {
        this.s3_masonry_image_url = s3_masonry_image_url;
    }


    public String getS3_image_url() {
        return s3_image_url;
    }


    public void setS3_image_url(String s3_image_url) {
        this.s3_image_url = s3_image_url;
    }


    public String getPlant_name() {
        return plant_name;
    }


    public void setPlant_name(String plant_name) {
        this.plant_name = plant_name;
    }

    public ImageFields[] getMissingImageFields() {
        
        String placementImage = this.placement_image;
        String s3Image = this.s3_image_url;
        String s3MasonryImage = this.s3_masonry_image_url;
        String fertilizationImage = this.fertilization_image;
        String propogationImage = this.propogation_image;
        List<ImageFields> missingImageFields = new ArrayList<>();

        if(placementImage.equals("tbd") || placementImage.equals("")) {
            missingImageFields.add(ImageFields.PLACEMENT_IMAGE);
        }

        if(s3Image.equals("tbd")|| s3Image.equals("")) {
            missingImageFields.add(ImageFields.S3_IMAGE_URL);
        }

        if(s3MasonryImage.equals("tbd")|| s3MasonryImage.equals("")) {
            missingImageFields.add(ImageFields.S3_MASONRY_IMAGE_URL);
        }

        if(fertilizationImage.equals("tbd") || fertilizationImage.equals("")) {
            missingImageFields.add(ImageFields.FERTILIZATION_IMAGE);
        }

        if(propogationImage.equals("tbd") || propogationImage.equals("")) {
            missingImageFields.add(ImageFields.PROPOGATION_IMAGE);
        }
        ImageFields[] missing = new ImageFields[missingImageFields.size()];

        missing = missingImageFields.toArray(missing);

        return missing;
    }
}

