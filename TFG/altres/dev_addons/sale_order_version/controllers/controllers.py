# -*- coding: utf-8 -*-
# from odoo import http


# class SaleOrderVersion(http.Controller):
#     @http.route('/sale_order_version/sale_order_version', auth='public')
#     def index(self, **kw):
#         return "Hello, world"

#     @http.route('/sale_order_version/sale_order_version/objects', auth='public')
#     def list(self, **kw):
#         return http.request.render('sale_order_version.listing', {
#             'root': '/sale_order_version/sale_order_version',
#             'objects': http.request.env['sale_order_version.sale_order_version'].search([]),
#         })

#     @http.route('/sale_order_version/sale_order_version/objects/<model("sale_order_version.sale_order_version"):obj>', auth='public')
#     def object(self, obj, **kw):
#         return http.request.render('sale_order_version.object', {
#             'object': obj
#         })

