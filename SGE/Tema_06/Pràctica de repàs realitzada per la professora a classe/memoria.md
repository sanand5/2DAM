## Crear el modulo:
`cd /mnt/extra-addons`
`odoo scaffold empresa`

``__manifest__.py`` cambiar autor por Andreu Sanz

## Ajustar permisos
```
access_empresa_empresa,empresa.empresa,model_empresa_empresa,base.group_user,1,1,1,1
pasa a ser :
access_empresa_incidencias,empresa.incidencias,model_empresa_incidencias,base.group_user,1,1,1,1
```

## Descomentar la linea 27 del manifest
`'security/ir.model.access.csv',`

## Descomentar el primer record iquitar los value en views.xml
```xml
<record model="ir.ui.view" id="empresa.list">
      <field name="name">Incidencias</field>
      <field name="model">empresa.incidencias</field>
      <field name="arch" type="xml">
        <tree>
          <field name="name"/>
        </tree>
      </field>
    </record>
```


## Una incidencia puedde estar en muchos departamentos