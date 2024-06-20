# -*- coding: utf-8 -*-
# from odoo import http


# class OctoTech(http.Controller):
#     @http.route('/octo_tech/octo_tech', auth='public')
#     def index(self, **kw):
#         return "Hello, world"

#     @http.route('/octo_tech/octo_tech/objects', auth='public')
#     def list(self, **kw):
#         return http.request.render('octo_tech.listing', {
#             'root': '/octo_tech/octo_tech',
#             'objects': http.request.env['octo_tech.octo_tech'].search([]),
#         })

#     @http.route('/octo_tech/octo_tech/objects/<model("octo_tech.octo_tech"):obj>', auth='public')
#     def object(self, obj, **kw):
#         return http.request.render('octo_tech.object', {
#             'object': obj
#         })
