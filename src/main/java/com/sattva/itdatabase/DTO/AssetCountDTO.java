package com.sattva.itdatabase.DTO;

public class AssetCountDTO {
    private String assetSubtype;
    private int inStock, outStock, totalStock;

    public AssetCountDTO(String assetSubtype, int intStock, int outStock, int totalStock) {
        this.assetSubtype = assetSubtype;
        this.inStock = intStock;
        this.outStock = outStock;
        this.totalStock = totalStock;
    }

    public void setAssetSubtype(String assetSubtype) {
        this.assetSubtype = assetSubtype;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public void setOutStock(int outStock) {
        this.outStock = outStock;
    }

    public void setTotalStock(int totalStock) {
        this.totalStock = totalStock;
    }

    public String getAssetSubtype() {
        return assetSubtype;
    }

    public int getInStock() {
        return inStock;
    }

    public int getOutStock() {
        return outStock;
    }

    public int getTotalStock() {
        return totalStock;
    }
}
