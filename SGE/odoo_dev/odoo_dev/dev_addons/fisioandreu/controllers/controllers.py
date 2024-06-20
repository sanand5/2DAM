# -*- coding: utf-8 -*-
# from odoo import http


# class Fisioandreu(http.Controller):
#     @http.route('/fisioandreu/fisioandreu', auth='public')
#     def index(self, **kw):
#         return "Hello, world"

#     @http.route('/fisioandreu/fisioandreu/objects', auth='public')
#     def list(self, **kw):
#         return http.request.render('fisioandreu.listing', {
#             'root': '/fisioandreu/fisioandreu',
#             'objects': http.request.env['fisioandreu.fisioandreu'].search([]),
#         })

#     @http.route('/fisioandreu/fisioandreu/objects/<model("fisioandreu.fisioandreu"):obj>', auth='public')
#     def object(self, obj, **kw):
#         return http.request.render('fisioandreu.object', {
#             'object': obj
#         })
