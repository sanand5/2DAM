# -*- coding: utf-8 -*-

from datetime import datetime, timedelta
from odoo import models, fields, api


# class todo(models.Model):
#     _name = 'todo.todo'
#     _description = 'todo.todo'

#     name = fields.Char()
#     value = fields.Integer()
#     value2 = fields.Float(compute="_value_pc", store=True)
#     description = fields.Text()
#
#     @api.depends('value')
#     def _value_pc(self):
#         for record in self:
#             record.value2 = float(record.value) / 100

class todo(models.Model):
    _name = 'todo.todo'
    _description = 'todo.todo'

    name = fields.Char(string="Nombre", required=True, help="lntroduzca el nombre de la tarea")
    descripcion = fields.Char(string="Descripción", help="lntroduzca la descipción de la tarea")
    fecha_limite = fields.Datetime(
        default=lambda self: (datetime.now() + timedelta(days=7)),
        string="Fecha Límite", 
        help="lntroduzca la fecha límite de la tarea"
        )
    asignado_a = fields.Many2many(
        'res.partner',  # Modelo de destino
        string='Asignado a',
        help="Seleccione el contacto al que se asignará la tarea"
    )
    
class contactos(models.Model):
     _name = 'res.partner'
     _inherit = 'res.partner'
