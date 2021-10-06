package com.case_type.model;

import java.util.List;

public interface Case_typeDAO_interface {
 	public void insert(Case_typeVO case_typeVO);
    public void update(Case_typeVO case_typeVO);
    public void delete(String csNo);
    public List<Case_typeVO> findByPrimaryKey(String csNo);
    public List<Case_typeVO> getAll();

}
