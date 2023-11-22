# Proyecto hibernate
## hibernate.cfg.xml
```xml
<!DOCTYPE hibernate-configuration PUBLIC
   "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
   "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
   <session-factory>
      <!-- JDBC Database connection settings -->
   <property name="connection.url">jdbc:mysql://127.0.0.1:3306/prueba?serverTimezone=UTC</property>

   <property name="connection.username">root</property>

   <property name="connection.password">root</property>

   <property name="connection.driver_class">com.mysql.cj.jdbc.Driver</property>

   <property name="dialect">org.hibernate.dialect.MySQLDialect</property>

   <mapping class="Usuario"/>  

   </session-factory>
</hibernate-configuration>

```
## persistence.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- Persistence deployment descriptor for dev profile -->
<persistence xmlns="http://java.sun.com/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_1_0.xsd"
             version="1.0">

    <persistence-unit name="hibernate-search-example">
        <properties>

            <property name="hibernate.dialect" value="org.hibernate.dialect.HSQLDialect"/>
            <property name="hibernate.connection.driver_class" value="org.hsqldb.jdbcDriver"/>
            <property name="hibernate.connection.username" value="sa"/>
            <property name="hibernate.connection.password" value=""/>
            <property name="hibernate.connection.url" value="jdbc:hsqldb:."/>

            <property name="hibernate.hbm2ddl.auto" value="create-drop"/>

            <property name="hibernate.search.default.indexBase" value="target"/>

            <!-- optional -->
            <property name="hibernate.search.default.directory_provider" value="filesystem"/>
        </properties>
    </persistence-unit>
</persistence>

```
## Usuario.java
```java
package prueba;


import javax.persistence.*;
@Entity
@Table(name = "user")
public class Usuario {
    @Column(name = "nombre")
    String nombre;

    public Usuario() {   
    }

    public Usuario(String nombre) {
        this.nombre = nombre;
    }
}

```
## Controller.java
```java
package prueba;

import org.hibernate.Session;

public class Controller {
    private static final Conexion conexion = new Conexion();
    public void fun() {
        
        Usuario user = new Usuario("Andreu");
        Session session = conexion.getSessionFactory().openSession();

        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        
        session.close();
    }
}

```
## Main.java
```java
package prueba;
public class Main {
    
    public static void main(String[] args) {
        Controller ctr = new Controller();
        ctr.fun();
    }
}

```
## Error
```
Exception in thread "main" org.hibernate.MappingException: Unknown entity: prueba.Usuario
        at org.hibernate.metamodel.model.domain.internal.MappingMetamodelImpl.entityPersister(MappingMetamodelImpl.java:529)
        at org.hibernate.internal.SessionImpl.getEntityPersister(SessionImpl.java:1418)
        at org.hibernate.event.internal.AbstractSaveEventListener.saveWithGeneratedId(AbstractSaveEventListener.java:114)
        at org.hibernate.event.internal.DefaultSaveOrUpdateEventListener.saveWithGeneratedOrRequestedId(DefaultSaveOrUpdateEventListener.java:192)
        at org.hibernate.event.internal.DefaultSaveEventListener.saveWithGeneratedOrRequestedId(DefaultSaveEventListener.java:36)
        at org.hibernate.event.internal.DefaultSaveOrUpdateEventListener.entityIsTransient(DefaultSaveOrUpdateEventListener.java:177)       
        at org.hibernate.event.internal.DefaultSaveEventListener.performSaveOrUpdate(DefaultSaveEventListener.java:30)
        at org.hibernate.event.internal.DefaultSaveOrUpdateEventListener.onSaveOrUpdate(DefaultSaveOrUpdateEventListener.java:73)
        at org.hibernate.event.service.internal.EventListenerGroupImpl.fireEventOnEachListener(EventListenerGroupImpl.java:101)
        at org.hibernate.internal.SessionImpl.fireSave(SessionImpl.java:619)
        at org.hibernate.internal.SessionImpl.save(SessionImpl.java:612)
        at org.hibernate.internal.SessionImpl.save(SessionImpl.java:607)
        at prueba.Controller.fun(Controller.java:13)
        at prueba.Main.main(Main.java:6)
```