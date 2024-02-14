# -*- coding: utf-8 -*-

from odoo import models, fields, api


class incidencias(models.Model):
    _name = 'empresa.incidencias'
    _description = 'empresa.incidencias'
    
    name = fields.Char()
    departamento = fields.Many2many("empresa.departamentos")


class departamentos(models.Model):
    _name = 'empresa.departamentos'
    _description = 'empresa.departamentos'
    
    name = fields.Char()
