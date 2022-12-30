package com.group6.tibame104.group.model;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

//@Repository
public class GroupHibernate implements GroupDAO_interface {
//	@PersistenceContext
	private Session session;

	@Override
	public void insert(GroupVO groupVO) {
		session.persist(groupVO);
	}

	@Override
	public void update(GroupVO groupVO) {
		session.update(groupVO);
	}

	@Override
	public void updateGroupQua(GroupVO groupVO) {
		session.update(groupVO);

	}

	@Override
	public void delete(Integer grouporderID) {
		session.remove(grouporderID);
	}

	@Override
	public GroupVO findByPrimaryKey(Integer groupBuyID) {
		// TODO Auto-generated method stub
		return session.get(GroupVO.class, groupBuyID);
	}

	@Override
	public List<GroupVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GroupVO> orderBy() {
		// TODO Auto-generated method stub
		return null;
	}

}
