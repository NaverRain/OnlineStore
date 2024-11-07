package com.naverrain.persistence.dao.impl;

import com.naverrain.persistence.dao.RoleDao;
import com.naverrain.persistence.dao.UserDao;
import com.naverrain.persistence.dto.RoleDto;
import com.naverrain.persistence.dto.UserDto;
import com.naverrain.persistence.utils.db.DBUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class MySqlJdbcUserDao implements UserDao {

    private RoleDao roleDao = new MySqlJdbcRoleDao();

    @Override
    public UserDto getUserById(int id) {
        try (var connection = DBUtils.getConnection();
                var ps = connection.prepareStatement("SELECT * FROM user WHERE id = ?")){
            ps.setInt(1, id);
            try (var res = ps.executeQuery()){
                if (res.next()){
                    return parseUserDto(res);
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        try (var connection = DBUtils.getConnection();
                var ps = connection.prepareStatement("SELECT * FROM user WHERE email = ?")){
            ps.setString(1, email);
            try (var res = ps.executeQuery()){
                if (res.next()){
                    return parseUserDto(res);
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<UserDto> getUsers() {
        try (var connection = DBUtils.getConnection();
             var ps = connection.prepareStatement("SELECT * FROM user");
             var res = ps.executeQuery()){
            List<UserDto> users = new ArrayList<>();
            while (res.next()){
                UserDto user = parseUserDto(res);

                users.add(user);
            }
            return users;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveUser(UserDto user) {
        try (var connection = DBUtils.getConnection();
             var ps = connection.prepareStatement(
                 "INSERT INTO user (first_name, last_name, email, fk_user_role, "
                 + "money, credit_card, password, partner_code, referrer_user_id) "
                 + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
            
            setUserFields(ps, user);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    @Override
	public UserDto getUserByPartnerCode(String partnerCode) {
		try (var connection = DBUtils.getConnection();
				var ps = connection.prepareStatement("SELECT * FROM user WHERE partner_code = ?")){
			ps.setString(1, partnerCode);
			try (var rs = ps.executeQuery()){
				if (rs.next()) {
					return parseUserDto(rs);
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

    @Override
    public void updateUser(UserDto user) {
        try (var connection = DBUtils.getConnection();
             var ps = connection.prepareStatement(
                 "UPDATE user SET first_name = ?, last_name = ?, email = ?, "
                 + "fk_user_role = ?, money = ?, credit_card = ?, password = ?, "
                 + "partner_code = ?, referrer_user_id = ? WHERE id = ?")) {
            
            setUserFields(ps, user);
            ps.setInt(10, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UserDto> getReferralsByUserId(int id) {
        try (var connection = DBUtils.getConnection();
                var ps = connection.prepareStatement("SELECT * FROM user WHERE referrer_user_id = ?")){
            ps.setInt(1, id);
            try (var rs = ps.executeQuery()){
                List<UserDto> users = new ArrayList<>();
                while (rs.next()){
                    UserDto user = parseUserDto(rs);
                    users.add(user);
                }
                return users;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private UserDto parseUserDto(ResultSet rs) {
        UserDto user = new UserDto();
        try {
            user.setId(rs.getInt("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            
            int roleId = rs.getInt("fk_user_role");
            if (roleId > 0) {
                user.setRoleDto(roleDao.getRoleById(roleId));
            } else {
                user.setRoleDto(null); // Or set a default role if necessary
            }
            
            user.setMoney(rs.getBigDecimal("money"));
            user.setCreditCard(rs.getString("credit_card"));
            user.setPassword(rs.getString("password"));
            user.setPartnerCode(rs.getString("partner_code"));
            
            int referrerId = rs.getInt("referrer_user_id");
            if (referrerId > 0) {
                user.setReferrerUser(this.getUserById(referrerId));
            } else {
                user.setReferrerUser(null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    private void setUserFields(PreparedStatement ps, UserDto user) throws SQLException {
        ps.setString(1, user.getFirstName());
        ps.setString(2, user.getLastName());
        ps.setString(3, user.getEmail());
        
        if (user.getRoleDto() != null && user.getRoleDto().getId() != null) {
            ps.setInt(4, user.getRoleDto().getId());
        } else if (user.getRoleDto() != null && !user.getRoleDto().getRoleName().isEmpty()) {
            RoleDto role = roleDao.getRoleByRoleName(user.getRoleDto().getRoleName());
            if (role != null) {
                ps.setInt(4, role.getId());
            } else {
                ps.setNull(4, Types.NULL);
            }
        } else {
            ps.setNull(4, Types.NULL);
        }
        
        ps.setBigDecimal(5, user.getMoney());
        ps.setString(6, user.getCreditCard());
        ps.setString(7, user.getPassword());
        ps.setString(8, user.getPartnerCode());
        
        if (user.getReferrerUser() != null) {
            ps.setInt(9, user.getReferrerUser().getId());
        } else {
            ps.setNull(9, Types.NULL);
        }
    }
}
