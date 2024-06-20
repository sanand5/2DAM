# -*- coding: utf-8 -*-

from odoo import models, fields, api


# class fisioandreu(models.Model):
#     _name = 'fisioandreu.fisioandreu'
#     _description = 'fisioandreu.fisioandreu'

#     name = fields.Char()
#     value = fields.Integer()
#     value2 = fields.Float(compute="_value_pc", store=True)
#     description = fields.Text()
#
#     @api.depends('value')
#     def _value_pc(self):
#         for record in self:
#             record.value2 = float(record.value) / 100


class pacientes(models.Model):
    _name = 'fisioandreu.pacientes'
    _description = 'fisioandreu.pacientes'

    name = fields.Char()
    apellidos = fields.Char()
    edad = fields.Integer()
    recomendaciones = fields.Many2many("fisioandreu.recomendaciones")
    
class recomendaciones(models.Model):
    _name = 'fisioandreu.recomendaciones'
    _description = 'fisioandreu.recomendaciones'

    name = fields.Char()