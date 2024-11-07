package com.naverrain.persistence.dao.impl;

import com.naverrain.persistence.dao.ProductDao;
import com.naverrain.persistence.dao.PurchaseDao;
import com.naverrain.persistence.dao.PurchaseStatusDao;
import com.naverrain.persistence.dao.UserDao;
import com.naverrain.persistence.dto.ProductDto;
import com.naverrain.persistence.dto.PurchaseDto;
import com.naverrain.persistence.dto.PurchaseStatusDto;
import com.naverrain.persistence.utils.db.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlJdbcPurchaseDao implements PurchaseDao {

    private ProductDao productDao = new MySqlJdbcProductDao();
    private UserDao userDao = new MySqlJdbcUserDao();
    private PurchaseStatusDao purchaseStatusDao = new MySqlJdbcPurchaseStatusDao();

    @Override
    public List<PurchaseDto> getPurchaseByUserId(int userId) {
        try (var connection = DBUtils.getConnection();
                var ps = connection.prepareStatement("SELECT * FROM purchase WHERE fk_purchase_user = ?")){
            ps.setInt(1, userId);

            List<PurchaseDto> purchases = new ArrayList<>();
            try (var res = ps.executeQuery()){
                while (res.next()){
                    PurchaseDto purchase = parsePurchase(connection, res);
                    purchases.add(purchase);
                }
            }
            return purchases;
        }
        catch (SQLException e){
            e.printStackTrace();
            return  null;
        }
    }

    @Override
    public List<PurchaseDto> getPurchases() {
        try (var connection = DBUtils.getConnection();
                var ps = connection.prepareStatement("SELECT * FROM purchase")){
            List<PurchaseDto> purchases = new ArrayList<>();
            try (var res = ps.executeQuery()){
                while (res.next()){
                    PurchaseDto purchase = parsePurchase(connection, res);
                    purchases.add(purchase);
                }

            }
            return purchases;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void savePurchase(PurchaseDto purchase) {
        try (var connection = DBUtils.getConnection();
                var ps = connection.prepareStatement("INSERT INTO purchase (fk_purchase_user, fk_purchase_purchase_status) VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS);
                var psPurchaseProduct = connection.prepareStatement("INSERT INTO purchase_product (purchase_id, product_id) VALUES (?, ?);")){
            ps.setInt(1, purchase.getUserDto().getId());
            ps.setInt(2, purchase.getPurchaseStatusDto().getId());
            ps.executeUpdate();

            try (var generatedKeys = ps.getGeneratedKeys()){
                if (generatedKeys.next()){

                    for (ProductDto product : purchase.getProductDtos()){
                        psPurchaseProduct.setLong(1, generatedKeys.getLong(1));
                        psPurchaseProduct.setInt(2, product.getId());
                        psPurchaseProduct.addBatch();
                    }

                    psPurchaseProduct.executeBatch();
                }
                else{
                    throw new SQLException("Purchase creating failed. Have no obtained ID");
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<PurchaseDto> getNotCompletedPurchases(Integer lastFulfilmentStageId) {
        try (var connection = DBUtils.getConnection();
                var ps = connection.prepareStatement("SELECT * FROM purchase WHERE fk_purchase_purchase_status != " + lastFulfilmentStageId)){
                List<PurchaseDto> purchases = new ArrayList<>();
                try (var rs = ps.executeQuery()){
                    while (rs.next()){
                        PurchaseDto purchase = parsePurchase(connection, rs);
                        purchases.add(purchase);
                    }
                }
                return purchases;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PurchaseDto getPurchaseById(Integer id) {
        try (var connection = DBUtils.getConnection();
             var ps = connection.prepareStatement("SELECT * FROM purchase WHERE id = ?")){
            ps.setInt(1, id);
            try (var rs = ps.executeQuery()){
                if (rs.next()){
                    return parsePurchase(connection, rs);
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updatePurchase(PurchaseDto purchaseDto) {
        try (var connection = DBUtils.getConnection();
             var ps = connection.prepareStatement("UPDATE `naver_db`.`purchase` SET `fk_purchase_purchase_status` = ? WHERE (`id` = ?);")){
                ps.setInt(1, purchaseDto.getPurchaseStatusDto().getId());
                ps.setInt(2, purchaseDto.getId());
                ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    private PurchaseDto parsePurchase(Connection connection, ResultSet rs) throws SQLException{
        PurchaseDto purchase = new PurchaseDto();
        purchase.setId(rs.getInt("id"));
        purchase.setUserDto(userDao.getUserById(rs.getInt("fk_purchase_user")));

        List<ProductDto> products = new ArrayList<>();
        try (var psProducts = connection.prepareStatement("SELECT * FROM purchase_product WHERE purchase_id = " + purchase.getId());
             var rsProducts = psProducts.executeQuery()) {

            while (rsProducts.next()) {
                products.add(productDao.getProductByProductId(rsProducts.getInt("product_id")));
            }
        }
        purchase.setProductDtos(products);
        purchase.setPurchaseStatusDto(purchaseStatusDao.getPurchaseStatusById(rs.getInt("fk_purchase_purchase_status")));
        return purchase;
    }
}
