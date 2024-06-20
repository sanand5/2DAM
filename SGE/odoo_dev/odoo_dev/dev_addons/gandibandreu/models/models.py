# -*- coding: utf-8 -*-

from odoo import models, fields, api
from datetime import datetime, timedelta
from odoo.exceptions import ValidationError
from odoo import _

class coches(models.Model):
    _name = 'gandibandreu.coches'
    _description = 'gandibandreu.coches'

    codigo = fields.Char(compute="_get_code", String = "Código")
    name = fields.Char(String = "Nombre")
    fecha = fields.Datetime(
        readonly=True,
        default=lambda self: (datetime.now()),
        string="Fecha de alta"
        )
    proveedor = fields.Many2one('gandibandreu.proveedores', string="Proveedor")
    
    def _get_code(self):
        for coches in self:
            try:
                coches.codigo = "CH_" + str(coches.id)
            except:
                raise ValidationError(_("Creacion de codigo errónea"))

class proveedores(models.Model):
    _name = 'gandibandreu.proveedores'
    _description = 'gandibandreu.proveedores'

    codigo = fields.Char(compute="_get_code", String = "Código")
    name = fields.Char(String = "Nombre")
    direccion = fields.Char(String = "Dirección", required=True)
    coches = fields.One2many('gandibandreu.coches', 'proveedor', string="Productos")
    
    def _get_code(self):
        for proveedores in self:
            try:
                proveedores.codigo = "PV_" + str(proveedores.id)
            except:
                raise ValidationError(_("Creacion de codigo errónea"))

class bancos(models.Model):
     _name = 'res.bank'
     _inherit = 'res.bank'