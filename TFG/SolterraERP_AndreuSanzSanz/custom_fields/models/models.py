# -*- coding: utf-8 -*-

from odoo import models, fields

class custom_fields(models.Model):
    _inherit = 'product.template'
    alto = fields.Float(string='Alto')
    ancho = fields.Float(string='Ancho')
    codigo_proveedor = fields.Char(string='Código del Proveedor')
    codigo_fabricante = fields.Char(string='Código del Fabricante')