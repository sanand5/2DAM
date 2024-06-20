from odoo import api, fields, models, _
from odoo.exceptions import ValidationError

class SaleOrder(models.Model):
    _inherit = 'sale.order'

    version_ids = fields.One2many('sale.order.version', 'sale_order_id', string='Versions')

    def action_create_version(self):
        for order in self:
            # Obtener datos del presupuesto actual
            order_data = {
                'partner_id': order.partner_id.id,
                'partner_invoice_id': order.partner_invoice_id.id,
                'partner_shipping_id': order.partner_shipping_id.id,
                'pricelist_id': order.pricelist_id.id,
                'order_line': [(0, 0, {
                    'product_id': line.product_id.id,
                    'name': line.name,
                    'product_uom_qty': line.product_uom_qty,
                    'product_uom': line.product_uom.id,
                    'price_unit': line.price_unit,
                }) for line in order.order_line]
            }

            # Crear una nueva versión del presupuesto
            new_order = self.env['sale.order'].create(order_data)
            
            if '- Version' in order.name:
                version_name = f'{order.name}.{len(order.version_ids) + 1}'
            else:
                version_name = f'{order.name} - Version {len(order.version_ids) + 1}'
            new_order.write({'name': version_name})
            
            # Asociar la nueva versión con el presupuesto original
            self.env['sale.order.version'].create({
                'sale_order_id': order.id,
                'version_order_id': new_order.id,
                'name': version_name,
                'date_created': fields.Datetime.now(),
            })


class SaleOrderVersion(models.Model):
    _name = 'sale.order.version'
    _description = 'Sale Order Version'

    sale_order_id = fields.Many2one(
        'sale.order', 
        string='Original Sale Order', 
        required=True, 
        ondelete='cascade'
        )
    version_order_id = fields.Many2one(
        'sale.order', 
        string='Version Sale Order', 
        required=True, 
        ondelete='cascade'
        )
    name = fields.Char(
        string='Version Name', 
        required=True, 
        readonly=True
        )
    date_created = fields.Datetime(
        string='Date Created', 
        default=fields.Datetime.now, 
        readonly=True
        )
