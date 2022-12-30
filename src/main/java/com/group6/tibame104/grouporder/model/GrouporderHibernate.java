package com.group6.tibame104.grouporder.model;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

//@Repository
public class GrouporderHibernate implements GrouporderDAO_interface {
//	@PersistenceContext
	private Session session;

	@Override
	public void insert(GrouporderVO grouporderVO) {
		session.persist(grouporderVO);

	}

	@Override
	public void update(GrouporderVO grouporderVO) {
		session.update(grouporderVO);
	}

	@Override
	public void delete(Integer grouporderID) {
//		final GrouporderVO grouporderVO = session.get(grouporderVO.class, grouporderID);
		session.remove(grouporderID);

	}

	@Override
	public GrouporderVO findByPrimaryKey(Integer groupBuyOrderID) {
		
		return session.get(GrouporderVO.class, groupBuyOrderID);
	}

	@Override
	public List<GrouporderVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GrouporderVO> getAllByMemID(Integer memberID) {
		// TODO Auto-generated method stub
		return null;
	}

}
