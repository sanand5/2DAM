# -*- coding: utf-8 -*-
from odoo import models, fields, api

class castellon(models.Model):
    _name = 'octo_tech.castellon'
    _description = 'octo_tech.castellon'

    name = fields.Char(string = "Localidad", required=True, help="lntroduzca la localidad")
    direccion = fields.Char()
    jefe = fields.Char(string = 'Jefe', required=True, help="lntroduzca el jefe")
    proyecto = fields.One2many('octo_tech.proyecto', 'sedeCastellon')
    
class proyecto(models.Model):
    _name = 'octo_tech.proyecto'
    _description = 'octo_tech.proyecto'

    name = fields.Char(string="Nombre", required=True, help="lntroduzca el nombre")
    descripcion = fields.Char()
    fecha_creacion = fields.Datetime(default=fields.Datetime.now)
    trabajador = fields.One2many('octo_tech.trabajador', 'proyecto')
    sedeCastellon = fields.Many2one(string = 'Sede', comodel_name='octo_tech.castellon', inverse_name = 'proyecto')
    
class trabajador(models.Model):
    _name = 'octo_tech.trabajador'
    _description = 'octo_tech.trabajador'

    name = fields.Char(string="Nombre", required=True, help="lntroduzca el nombre")
    apellidos = fields.Char(string="Apellidos",readonly=False, required=True, help="lntroduzca los apellidos")
    fecha_antiguedad = fields.Datetime()
    sueldo = fields.Integer()
    proyecto = fields.Many2one(comodel_name='octo_tech.proyecto', inverse_name = 'trabajador')
    