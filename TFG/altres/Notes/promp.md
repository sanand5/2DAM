> Quiero que actues como un experto en el desarrollo de modulos de Odoo 17. Esta es una pequeña descripción de lo que quiero que haga el modulo que estoy desarrollando.
---
Quiero crear un modulo para poder clasificar comentarios de internet, para ello quiero que el modulo disponga de tres ventanas.
La primera ventana separa para clasificar con etiquetas multietiqueta, en esta ventana saldra un listado de documentos (datasets) y dentro de estos datasets tengan un listado de comentarios y sus categorias. 
 - Los comentarios pueden ser multi etiqueta
 - Los cometarios tendran dos campos, COMENTARIO y etiquetas
La segunda ventana tendra etiquetas y por cada documento se podran definir distintas etiquetas.
 - Estas etiquetas tendran dos campos, nombre y descripcion
La tercera ventana sera una ventana para visualizar de manera grafica los resultados, deberas implementar un grafico para poder mostrar las frecuencias de cada codigo en cada dataset
---
> Para hacer lo siguiente he creado un modulo y este es el contenido de los archivos.

**__manifest__.py**
```python
{
    'name': 'Comment Classifier',
    'version': '1.0',
    'summary': 'Module for classifying internet comments with multi-label tags',
    'category': 'Tools',
    'author': 'Your Name',
    'depends': ['base'],
    'data': [
        'security/ir.model.access.csv',
        'views/comment_views.xml',
        'views/tag_views.xml',
        'views/analysis_views.xml',
        'views/dataset_views.xml',
    ],
    'application': True,
}
```

**comment.py**
```python
from odoo import models, fields

class Comment(models.Model):
    _name = 'comment.classifier.comment'
    _description = 'Comment'

    name = fields.Char(string='Comment')
    tag_ids = fields.Many2many('comment.classifier.tag', string='Tags')
    dataset_id = fields.Many2one('comment.classifier.dataset', string='Dataset')

class Dataset(models.Model):
    _name = 'comment.classifier.dataset'
    _description = 'Dataset'

    name = fields.Char(string='Dataset Name')
    comment_ids = fields.One2many('comment.classifier.comment', 'dataset_id', string='Comments')
```

**tag.py**
```python
from odoo import models, fields

class Tag(models.Model):
    _name = 'comment.classifier.tag'
    _description = 'Tag'

    name = fields.Char(string='Name', required=True)
    description = fields.Text(string='Description')
    comment_ids = fields.Many2many('comment.classifier.comment', string='Comments')

```

**comment_views.xml**
```xml
<odoo>
  <record id="view_comment_form" model="ir.ui.view">
    <field name="name">comment.form</field>
    <field name="model">comment.classifier.comment</field>
    <field name="arch" type="xml">
      <form>
        <sheet>
          <group>
            <field name="name" />
            <field name="tag_ids" widget="many2many_tags" />
            <field name="dataset_id" />
          </group>
        </sheet>
      </form>
    </field>
  </record>

  <record id="view_comment_tree" model="ir.ui.view">
    <field name="name">comment.tree</field>
    <field name="model">comment.classifier.comment</field>
    <field name="arch" type="xml">
      <tree>
        <field name="name" />
        <field name="tag_ids" />
        <field name="dataset_id" />
      </tree>
    </field>
  </record>

  <record id="action_comment" model="ir.actions.act_window">
    <field name="name">Comments</field>
    <field name="res_model">comment.classifier.comment</field>
    <field name="view_mode">tree,form</field>
  </record>

  <menuitem id="menu_comment_classifier_root" name="Comment Classifier" />
  <menuitem id="menu_comment" parent="menu_comment_classifier_root" name="Comments"
    action="action_comment" />
</odoo>
```

**analysis_views.xml**
```xml
<odoo>
  <!-- Vista gráfica para el análisis de comentarios -->
  <record id="view_analysis_graph" model="ir.ui.view">
    <field name="name">analysis.graph</field>
    <field name="model">comment.classifier.comment</field>
    <field name="arch" type="xml">
      <graph string="Comment Analysis" type="bar">
        <field name="dataset_id" type="col" />
        <field name="tag_ids" type="row" />
        <field name="id" type="measure" operator="count" />
      </graph>
    </field>
  </record>

  <!-- Acción para la vista gráfica de análisis -->
  <record id="action_analysis" model="ir.actions.act_window">
    <field name="name">Analysis</field>
    <field name="res_model">comment.classifier.comment</field>
    <field name="view_mode">graph</field>
    <field name="view_id" ref="view_analysis_graph" />
    <field name="context">{'group_by': ['dataset_id', 'tag_ids']}</field>
  </record>

  <!-- Menú para acceder a la vista de análisis -->
  <menuitem id="menu_analysis" parent="menu_comment_classifier_root" name="Analysis"
    action="action_analysis" />
</odoo>
```

**dataset_views.xml**
```xml
<!-- views/dataset_views.xml -->
<odoo>
  <record id="view_dataset_form" model="ir.ui.view">
    <field name="name">dataset.form</field>
    <field name="model">comment.classifier.dataset</field>
    <field name="arch" type="xml">
      <form>
        <sheet>
          <group>
            <field name="name" />
            <field name="comment_ids">
              <tree>
                <field name="name" />
                <field name="tag_ids" />
              </tree>
            </field>
          </group>
        </sheet>
      </form>
    </field>
  </record>

  <record id="view_dataset_tree" model="ir.ui.view">
    <field name="name">dataset.tree</field>
    <field name="model">comment.classifier.dataset</field>
    <field name="arch" type="xml">
      <tree>
        <field name="name" />
      </tree>
    </field>
  </record>

  <record id="action_dataset" model="ir.actions.act_window">
    <field name="name">Datasets</field>
    <field name="res_model">comment.classifier.dataset</field>
    <field name="view_mode">tree,form</field>
  </record>

  <menuitem id="menu_dataset" parent="menu_comment_classifier_root" name="Datasets"
    action="action_dataset" />
</odoo>
```

**tag_views.xml**
```xml
<odoo>
  <record id="view_tag_form" model="ir.ui.view">
    <field name="name">tag.form</field>
    <field name="model">comment.classifier.tag</field>
    <field name="arch" type="xml">
      <form>
        <sheet>
          <group>
            <field name="name" />
            <field name="description" />
          </group>
        </sheet>
      </form>
    </field>
  </record>

  <record id="view_tag_tree" model="ir.ui.view">
    <field name="name">tag.tree</field>
    <field name="model">comment.classifier.tag</field>
    <field name="arch" type="xml">
      <tree>
        <field name="name" />
        <field name="description" />
      </tree>
    </field>
  </record>

  <record id="action_tag" model="ir.actions.act_window">
    <field name="name">Tags</field>
    <field name="res_model">comment.classifier.tag</field>
    <field name="view_mode">tree,form</field>
  </record>

  <menuitem id="menu_tag" parent="menu_comment_classifier_root" name="Tags" action="action_tag" />
</odoo>
```

**ir.model.access.csv**
```csv
id,name,model_id:id,group_id:id,perm_read,perm_write,perm_create,perm_unlink
access_comment,access_comment,model_comment_classifier_comment,,1,1,1,1
access_tag,access_tag,model_comment_classifier_tag,,1,1,1,1
access_dataset,access_dataset,model_comment_classifier_dataset,,1,1,1,1
```
> Modifica lo necesario para que en las vistas de los graficos se genere un grafico por cada documento existente