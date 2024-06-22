package com.mycompany.persistenciabdemp;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-01-26T17:11:31", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Emp.class)
public class Emp_ { 

    public static volatile SingularAttribute<Emp, String> ename;
    public static volatile SingularAttribute<Emp, BigDecimal> comm;
    public static volatile SingularAttribute<Emp, Integer> mgr;
    public static volatile SingularAttribute<Emp, Integer> empno;
    public static volatile SingularAttribute<Emp, String> job;
    public static volatile SingularAttribute<Emp, Date> hiredate;
    public static volatile SingularAttribute<Emp, Integer> deptno;
    public static volatile SingularAttribute<Emp, BigDecimal> sal;

}