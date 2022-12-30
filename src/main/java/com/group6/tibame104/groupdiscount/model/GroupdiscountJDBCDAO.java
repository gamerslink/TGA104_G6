package com.group6.tibame104.groupdiscount.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GroupdiscountJDBCDAO implements GroupdiscountDAO_interface {
	@Autowired
	private DataSource dataSource;

	private static final String Insert = "INSERT INTO groupBuyDiscount(groupBuyID,groupBuyProductOrderTotal,groupBuyCount) VALUES (?,?,?)";
	private static final String Update = "UPDATE groupBuyDiscount set groupBuyID = ?, groupBuyProductOrderTotal = ?, groupBuyCount = ? where countTableID = ?";
	private static final String Delete = "DELETE FROM groupBuyDiscount where countTableID = ?";
	private static final String GetAllByCountTableID = "SELECT countTableID,groupBuyID,groupBuyProductOrderTotal,groupBuyCount FROM groupBuyDiscount WHERE countTableID = ?;";
	private static final String GetAllByGroupBuyID = "SELECT countTableID,groupBuyID,groupBuyProductOrderTotal,groupBuyCount FROM groupBuyDiscount WHERE groupBuyID = ?;";
	private static final String GetAll = "SELECT countTableID,groupBuyID,groupBuyProductOrderTotal,groupBuyCount FROM groupBuyDiscount;";
//	private static final String GET_ALL_GBID = "SELECT distinct groupBuyID FROM groupBuyDiscount;";

	@Override
	public void insert(GroupdiscountVO groupdiscountVO) {
		try (Connection con = dataSource.getConnection(); PreparedStatement pstmt = con.prepareStatement(Insert)) {

			pstmt.setInt(1, groupdiscountVO.getGroupBuyID());
			pstmt.setInt(2, groupdiscountVO.getGroupBuyProductOrderTotal());
			pstmt.setDouble(3, groupdiscountVO.getGroupBuyCount());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 更新
	@Override
	public void update(GroupdiscountVO groupdiscountVO) {

		try (Connection con = dataSource.getConnection(); PreparedStatement pstmt = con.prepareStatement(Update)) {

			pstmt.setInt(1, groupdiscountVO.getGroupBuyID());
			pstmt.setInt(2, groupdiscountVO.getGroupBuyProductOrderTotal());
			pstmt.setDouble(3, groupdiscountVO.getGroupBuyCount());
			pstmt.setInt(4, groupdiscountVO.getCountTableID());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 刪除
	@Override
	public void delete(Integer countTableID) {

		try (Connection con = dataSource.getConnection(); PreparedStatement pstmt = con.prepareStatement(Delete)) {

			pstmt.setInt(1, countTableID);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 查詢單一
	@Override
	public GroupdiscountVO findByPK(Integer countTableID) {
		try (Connection con = dataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(GetAllByCountTableID);) {
			pstmt.setInt(1, countTableID);
			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					GroupdiscountVO groupdiscountVO = new GroupdiscountVO();
					groupdiscountVO.setCountTableID(rs.getInt("countTableID"));
					groupdiscountVO.setGroupBuyID(rs.getInt("groupBuyID"));
					groupdiscountVO.setGroupBuyProductOrderTotal(rs.getInt("groupBuyProductOrderTotal"));
					groupdiscountVO.setGroupBuyCount(rs.getDouble("groupBuyCount"));
					return groupdiscountVO;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	// 用groupBuyID搜尋所有countTable
	public List<GroupdiscountVO> findAllByGroupBuyID(Integer groupBuyID) {
		try (Connection con = dataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(GetAllByGroupBuyID);) {
			try (ResultSet rs = pstmt.executeQuery();) {
				List<GroupdiscountVO> list = new ArrayList<GroupdiscountVO>();
				while (rs.next()) {
					GroupdiscountVO groupdiscountVO = new GroupdiscountVO();
					groupdiscountVO.setCountTableID(rs.getInt("countTableID"));
					groupdiscountVO.setGroupBuyID(rs.getInt("groupBuyID"));
					groupdiscountVO.setGroupBuyProductOrderTotal(rs.getInt("groupBuyProductOrderTotal"));
					groupdiscountVO.setGroupBuyCount(rs.getDouble("groupBuyCount"));
					list.add(groupdiscountVO);
				}
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// 查詢ALL

	public List<GroupdiscountVO> findAll() {
		try (Connection con = dataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(GetAll);) {
			try (ResultSet rs = pstmt.executeQuery();) {
				List<GroupdiscountVO> list = new ArrayList<GroupdiscountVO>();
				while (rs.next()) {
					GroupdiscountVO groupdiscountVO = new GroupdiscountVO();

					groupdiscountVO.setCountTableID(rs.getInt("countTableID"));
					groupdiscountVO.setGroupBuyID(rs.getInt("groupBuyID"));
					groupdiscountVO.setGroupBuyProductOrderTotal(rs.getInt("groupBuyProductOrderTotal"));
					groupdiscountVO.setGroupBuyCount(rs.getDouble("groupBuyCount"));
					list.add(groupdiscountVO); // Store the row in the list
				}
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
//	public List<GroupdiscountVO> findAllByCountTableID(Integer countTableID) {
//
//		try (Connection con = dataSource.getConnection();
//				PreparedStatement pstmt = con.prepareStatement(GetAllByCountTableID);) {
//			try (ResultSet rs = pstmt.executeQuery();) {
//				List<GroupdiscountVO> list = new ArrayList<GroupdiscountVO>();
//				while (rs.next()) {
//					GroupdiscountVO groupdiscountVO = new GroupdiscountVO();
//					groupdiscountVO.setGroupBuyID(rs.getInt("groupBuyID"));
//					list.add(groupdiscountVO); // Store the row in the list
//				}
//				return list;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	
	
//	// 找有的groupBuyID
//	public List<GroupdiscountVO> getGroupBuyID() {
//		try (Connection con = dataSource.getConnection();
//				PreparedStatement pstmt = con.prepareStatement(GET_ALL_GBID);) {
//			try (ResultSet rs = pstmt.executeQuery();) {
//				List<GroupdiscountVO> list = new ArrayList<GroupdiscountVO>();
//				while (rs.next()) {
//					GroupdiscountVO groupdiscountVO = new GroupdiscountVO();
//					groupdiscountVO.setGroupBuyID(rs.getInt("groupBuyID"));
//					list.add(groupdiscountVO); // Store the row in the list
//				}
//				return list;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}



//	public static void main(String[] args) {
//
//		GroupdiscountJDBCDAO dao = new GroupdiscountJDBCDAO();
//
////新增
//		GroupdiscountVO groupdiscountVO = new GroupdiscountVO();
//
//		groupdiscountVO.setGroupBuyID(1);
//		groupdiscountVO.setGroupBuyProductOrderTotal(1000);
//		groupdiscountVO.setGroupBuyCount(0.99);
//		
//		dao.insert(groupdiscountVO);

////    修改		
//		GroupdiscountVO groupdiscountVO2 = new GroupdiscountVO();
//	
//		groupdiscountVO2.setCountTableID(1);
//		groupdiscountVO2.setGroupBuyID(1);
//		groupdiscountVO2.setGroupBuyProductOrderTotal(1);
//		groupdiscountVO2.setGroupBuyCount(0.1);
//		
//		dao.update(groupdiscountVO2);
////	刪除
//		dao.delete(3);
//    單一查詢
//		GroupdiscountVO groupdiscountVO3 = dao.findByPrimaryKey(1);
//
//		System.out.print(groupdiscountVO3.getCountTableID() + ",");
//		System.out.print(groupdiscountVO3.getGroupBuyID() + ",");
//		System.out.print(groupdiscountVO3.getGroupBuyProductOrderTotal() + ",");
//		System.out.print(groupdiscountVO3.getGroupBuyCount() + ",");
//
//		System.out.println("---------------------");
////    查詢all
//		List<GroupdiscountVO> list = dao.getAll();
//		for (GroupdiscountVO aEmp : list) {
//			System.out.print(aEmp.getCountTableID() + ",");
//			System.out.print(aEmp.getGroupBuyID() + ",");
//			System.out.print(aEmp.getGroupBuyProductOrderTotal() + ",");
//			System.out.print(aEmp.getGroupBuyCount());
//			
//			System.out.println();
//		}
//		查詢all
//		List<GroupdiscountVO> list = dao.getCountTable();
//		for (GroupdiscountVO aEmp : list) {
//			
//			System.out.print(aEmp.getCountTableID() + ",");
//			System.out.print(aEmp.getGroupBuyID() + ",");
//			System.out.print(aEmp.getGroupBuyProductOrderTotal() + ",");
//			System.out.print(aEmp.getGroupBuyCount());
//			
//			System.out.println();
//		}
////		 查詢groupBuyID
//			List<GroupdiscountVO> list = dao.getGroupBuyID();
//			for (GroupdiscountVO aEmp : list) {
//	
//				System.out.print(aEmp.getGroupBuyID() + ",");
//
//				
//				System.out.println();
//			}
//	}
}