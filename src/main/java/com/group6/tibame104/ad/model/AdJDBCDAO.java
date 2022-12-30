package com.group6.tibame104.ad.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdJDBCDAO implements AdDAO_interface {
	@Autowired
	private DataSource dataSource;

	private static final String INSERT_STMT = "INSERT INTO advertise (administratorID, groupBuyID, adTitle, adType, adDescribe, adStartDate, adEndDate, adPhoto) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT adSerialID, administratorID, groupBuyID, adTitle, adType, adDescribe, adStartDate, adEndDate, adPhoto, updateTime FROM advertise order by adSerialID";
	private static final String GET_ONE_STMT = "SELECT adSerialID, administratorID, groupBuyID, adTitle, adType, adDescribe, adStartDate, adEndDate, adPhoto, updateTime FROM advertise where adSerialID = ?";
	private static final String DELETE = "DELETE FROM advertise where adSerialID = ?";
	private static final String UPDATE = "UPDATE advertise set adTitle=?,  adType=?, adDescribe=?, adPhoto=?, adStartDate=?, adEndDate=?, updateTime=now() where adSerialID= ?";

	@Override
	public void insert(AdVO adVO) {

		try (
			Connection con = dataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(INSERT_STMT);
			) {
			pstmt.setInt(1, adVO.getAdministratorID());
			pstmt.setInt(2, adVO.getGroupBuyID());
			pstmt.setString(3, adVO.getAdTitle());
			pstmt.setString(4, adVO.getAdType());
			pstmt.setString(5, adVO.getAdDescribe());
			pstmt.setDate(6, adVO.getAdStartDate());
			pstmt.setDate(7, adVO.getAdEndDate());
			pstmt.setBytes(8, adVO.getAdPhoto());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(AdVO adVO) {

		try (
			Connection con = dataSource.getConnection(); 
			PreparedStatement pstmt = con.prepareStatement(UPDATE);
			) {
			pstmt.setString(1, adVO.getAdTitle());
			pstmt.setString(2, adVO.getAdType());
			pstmt.setString(3, adVO.getAdDescribe());
			pstmt.setBytes(4, adVO.getAdPhoto());
			pstmt.setDate(5, adVO.getAdStartDate());
			pstmt.setDate(6, adVO.getAdEndDate());
			pstmt.setInt(7, adVO.getAdSerialID());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer adSerialID) {

		try (
			Connection con = dataSource.getConnection(); 
			PreparedStatement pstmt = con.prepareStatement(DELETE);
			) {
			pstmt.setInt(1, adSerialID);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public AdVO findByPrimaryKey(Integer adSerialID) {
		try (
			Connection con = dataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT);
			) {
			pstmt.setInt(1, adSerialID);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					AdVO adVO = new AdVO();
					adVO.setAdSerialID(rs.getInt("adSerialID"));
					adVO.setAdministratorID(rs.getInt("administratorID"));
					adVO.setGroupBuyID(rs.getInt("groupBuyID"));
					adVO.setAdTitle(rs.getString("adTitle"));
					adVO.setAdType(rs.getString("adType"));
					adVO.setAdDescribe(rs.getString("adDescribe"));
					adVO.setAdPhoto(rs.getBytes("adPhoto"));
					adVO.setAdStartDate(rs.getDate("adStartDate"));
					adVO.setAdEndDate(rs.getDate("adEndDate"));
					adVO.setUpdateTime(rs.getTimestamp("updateTime"));
					return adVO;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AdVO> getAll() {
		try (
			Connection con = dataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);
			) {
			try (
				ResultSet rs = pstmt.executeQuery()) {
				List<AdVO> list = new ArrayList<AdVO>();
				while (rs.next()) {
					AdVO adVO = new AdVO();
					adVO.setAdSerialID(rs.getInt("adSerialID"));
					adVO.setAdministratorID(rs.getInt("administratorID"));
					adVO.setGroupBuyID(rs.getInt("groupBuyID"));
					adVO.setAdTitle(rs.getString("adTitle"));
					adVO.setAdType(rs.getString("adType"));
					adVO.setAdDescribe(rs.getString("adDescribe"));
					adVO.setAdPhoto(rs.getBytes("adPhoto"));
					adVO.setAdStartDate(rs.getDate("adStartDate"));
					adVO.setAdEndDate(rs.getDate("adEndDate"));
					adVO.setUpdateTime(rs.getTimestamp("updateTime"));
					list.add(adVO);
				}
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
//	public static void main(String[] args) {

//		AdJDBCDAO dao = new AdJDBCDAO();
		// 圖片上傳
//			byte[] data = null;
//			FileImageInputStream input = null;
//			try {
//				input = new FileImageInputStream(new File("C:\\Users\\Tibame_T14\\Desktop\\001.jpg"));
//				ByteArrayOutputStream output = new ByteArrayOutputStream();
//				byte[] buf = new byte[1024];
//				int numBytesRead = 0;
//				while ((numBytesRead = input.read(buf)) != -1) {
//					output.write(buf, 0, numBytesRead);
//				}
//				data = output.toByteArray();
//				output.close();
//				input.close();
//			} catch (Exception ex1) {
//				ex1.printStackTrace();
//			}

		// 新增
//			 AdVO AdVO1 = new AdVO();
//			 AdVO1.setAdSerialID(1);
//			 AdVO1.setAdministratorID(2);
//			 AdVO1.setGroupBuyID(1);
//			 AdVO1.setAdTitle("最新消息");
//			 AdVO1.setAdType("團購相關");
//			 AdVO1.setAdDescribe("雙11團購優惠開跑");
////			 AdVO1.setAdPhoto(data);
//			 AdVO1.setAdStartDate(java.sql.Date.valueOf("2005-01-01"));
//			 AdVO1.setAdEndDate(java.sql.Date.valueOf("2005-01-20"));
//			 AdVO1.setUpdateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
//			  
//			 dao.insert(AdVO1);

//			// 修改
//			 AdVO AdVO2 = new  AdVO();
//			 AdVO2.setAdSerialID(1);
//			 AdVO2.setAdTitle("最新消息");
//			 AdVO2.setAdType("團購相關");
//			 AdVO2.setAdDescribe("雙11團購優惠開跑");
//			 AdVO2.setAdPhoto(data);
//			 AdVO2.setAdStartDate(java.sql.Date.valueOf("2022-01-01"));
//			 AdVO2.setAdEndDate(java.sql.Date.valueOf("2022-11-30"));
////			 AdVO2.setUpdateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
//			 dao.update(AdVO2);

//			// 刪除
//			dao.delete(1);

		// 查詢
//			AdVO adVO3 = dao.findByPrimaryKey("adSerialID");
//			System.out.print(adVO3.getAdSerialID() + ",");
//			System.out.print(adVO3.getAdministratorID() + ",");
//			System.out.print(adVO3.getGroupBuyID() + ",");
//			System.out.print(adVO3.getAdTitle() + ",");
//			System.out.print(adVO3.getAdType() + ",");
//			System.out.print(adVO3.getAdDescribe() + ",");
//			System.out.print(adVO3.getAdPhoto() + ",");
//			System.out.print(adVO3.getAdStartDate());
//			System.out.print(adVO3.getAdEndDate());
//			System.out.print(adVO3.getUpdateTime());
//			System.out.println("---------------------");

//			// 查詢
//			List<AdVO> list = dao.getAll();
//			for (AdVO aAdver : list) {
//				System.out.print(aAdver.getAdSerialID() + ",");
//				System.out.print(aAdver.getAdministratorID() + ",");
//				System.out.print(aAdver.getGroupBuyID() + ",");
//				System.out.print(aAdver.getAdTitle() + ",");
//				System.out.print(aAdver.getAdType() + ",");
//				System.out.print(aAdver.getAdDescribe() + ",");
//				System.out.print(aAdver.getAdPhoto() + ",");
//				System.out.print(aAdver.getAdStartDate());
//				System.out.print(aAdver.getAdEndDate());
//				System.out.print(aAdver.getUpdateTime());
//				System.out.println();
//			}

//	}

}
