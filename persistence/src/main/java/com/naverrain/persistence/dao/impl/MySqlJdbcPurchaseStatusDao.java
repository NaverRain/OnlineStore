package com.naverrain.persistence.dao.impl;

import com.naverrain.persistence.dao.PurchaseStatusDao;
import com.naverrain.persistence.dto.PurchaseStatusDto;
import com.naverrain.persistence.utils.db.DBUtils;

import java.sql.SQLException;

public class MySqlJdbcPurchaseStatusDao implements PurchaseStatusDao {



    @Override
    public PurchaseStatusDto getPurchaseStatusById(Integer id) {
        try (var connection = DBUtils.getConnection();
                var ps = connection.prepareStatement("SELECT * FROM purchase_status WHERE id = ?")){
            ps.setInt(1, id);
            try (var rs = ps.executeQuery()){
                if (rs.next()){
                    var purchaseStatusDto = new PurchaseStatusDto();
                    purchaseStatusDto.setId(rs.getInt("id"));
                    purchaseStatusDto.setPurchaseStatus(rs.getString("status_name"));
                    return purchaseStatusDto;
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
