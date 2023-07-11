class TheGreatCherryTree(InstantEffect):

    def __init__(self, x_centre, y_centre, z_centre, tick0, list_return, cmd_builder: cmd_IO.CmdBuilder,main_brand_func:InterpolationFunction,h_main_brand,
                 main_brand,    sub_brand_01, zy_func,len_sub_01_brand_range,r_sub_01_brand_func,
                 r_sub_01_factor_deviation_range,h_sub_01_brand_range_start,z_sub_01_deviation_range,
                 num_sub_02_brand,rotation_zy_sub_02_range,len_sub_02_brand_range,r_sub_02_brand_func,r_sub_02_factor_deviation_range,
                 h_sub_02_range_start,z_dub_02_deviation_range,rotation_y_d_sub_02,num_sub_03_brand,rotation_zy_sub_03_range,len_sub_03_brand_range,r_sub_03_brand_func,r_sub_03_factor_deviation_range,
                 h_sub_03_range_start,z_dub_03_deviation_range,rotation_y_d_sub_03,bcp_leave,leave_density_func,r_density_range,r_density_sigma,out_pur_air=True):
        super().__init__(x_centre, y_centre, z_centre, tick0, list_return, cmd_builder)
        self.bcp_leave = bcp_leave
        self.out_put_air = out_pur_air
        self.leave_density_func = leave_density_func
        self.r_density_range = r_density_range
        self.r_density_sigma = r_density_sigma
        self.num_sub_03_brand = num_sub_03_brand
        self.rotation_zy_sub_03_range = rotation_zy_sub_03_range
        self.len_sub_03_brand_range = len_sub_03_brand_range
        self.r_sub_03_brand_func = r_sub_03_brand_func
        self.r_sub_03_factor_deviation_range = r_sub_03_factor_deviation_range
        self.h_sub_03_range_start = h_sub_03_range_start
        self.z_dub_03_deviation_range = z_dub_03_deviation_range
        self.rotation_y_d_sub_03 = rotation_y_d_sub_03
        self.num_sub_02_brand = num_sub_02_brand
        self.rotation_zy_sub_02_range = rotation_zy_sub_02_range
        self.len_sub_02_brand_range = len_sub_02_brand_range
        self.r_sub_02_brand_func = r_sub_02_brand_func
        self.r_sub_02_factor_deviation_range = r_sub_02_factor_deviation_range
        self.h_sub_02_range_start = h_sub_02_range_start
        self.z_dub_02_deviation_range = z_dub_02_deviation_range
        self.sub_brand_01 = sub_brand_01
        self.zy_func = zy_func
        self.len_sub_01_brand_range = len_sub_01_brand_range
        self.r_sub_01_brand_func = r_sub_01_brand_func
        self.r_sub_01_factor_deviation_range = r_sub_01_factor_deviation_range
        self.h_sub_01_brand_range_start = h_sub_01_brand_range_start
        self.z_sub_01_deviation_range = z_sub_01_deviation_range
        self.main_brand_func = main_brand_func
        self.h_main_brand = h_main_brand
        self.rotation_y_d_sub_02 = rotation_y_d_sub_02
        self.main_brand = main_brand

        self.obj = Obj([])

    def tick(self):
        if self.tick_now < self.tick1:
            for i in range(self.h_main_brand):
                r = self.main_brand_func.tick(i / (self.h_main_brand - 1))
                self.obj.obj_extend(circle_get(r,2,self.main_brand).xy_rotate(90),y=i)

            for i in range(self.sub_brand_01):
                rotation_xz = (i / self.sub_brand_01) * 360 + random.randint(-10,10)
                len_brand_t = random.uniform(self.len_sub_01_brand_range[0],self.len_sub_01_brand_range[1])
                y_s = random.uniform(self.h_sub_01_brand_range_start[0],self.h_sub_01_brand_range_start[1])
                r_t = self.main_brand_func.tick(y_s / self.h_main_brand) * 0.1
                x_s,z_s = xy_get(r_t,rotation_xz)
                obj_brand_t,xyz_list_t = self.xy_brand_obj_get(len_brand_t,rotation_xz)

                for ii in range(self.num_sub_02_brand):
                    len_start_t = random.uniform(self.h_sub_02_range_start[0],self.h_sub_02_range_start[1])
                    index_t = int(len_start_t * 2)
                    x_t,y_t,z_t = xyz_list_t[index_t][0],xyz_list_t[index_t][1],xyz_list_t[index_t][2]
                    rotation_y_t,rotation_xz_t = list_rotation_get(xyz_list_t)[index_t][0],list_rotation_get(xyz_list_t)[index_t][1]
                    len_brand_t_t = random.uniform(self.len_sub_02_brand_range[0],self.len_sub_02_brand_range[1])
                    rotation_xz_set = rotation_xz_t + (ii / self.num_sub_03_brand) * 360 + (180 / self.num_sub_03_brand) + random.randint(-30,30)
                    obj_t,xyz_list_t_t = self.xy_brand_obj_02_get(len_brand_t_t,rotation_y_t,rotation_xz_t,rotation_xz_set)
                    for iii in range(self.num_sub_03_brand):
                        while True:
                            len_start_t_t = random.uniform(self.h_sub_03_range_start[0],self.h_sub_03_range_start[1])
                            if len_start_t_t < len_brand_t_t:
                                break
                        index_t_t = int(len_start_t_t * 2)
                        index_t_t = index_t_t if index_t_t < len(xyz_list_t_t) else len(xyz_list_t_t) - 1
                        x_t_t,y_t_t,z_t_t = xyz_list_t_t[index_t_t][0],xyz_list_t_t[index_t_t][1],xyz_list_t_t[index_t_t][2]
                        rotation_y_t_t, rotation_xz_t_t = list_rotation_get(xyz_list_t_t)[index_t_t][0], list_rotation_get(xyz_list_t_t)[index_t_t][1]
                        len_brand_t_t_t = random.uniform(self.len_sub_03_brand_range[0],self.len_sub_03_brand_range[1])
                        obj_t_t,xyz_list_t_t_t = self.xy_brand_obj_03_get(len_brand_t_t_t, rotation_y_t_t, rotation_xz_t_t)
                        rotation_info = list_rotation_get(xyz_list_t_t_t)
                        for iiii in range(len(xyz_list_t_t_t)):
                            if math_helper.possibility(self.leave_density_func.tick(iiii / (len(xyz_list_t_t_t)))):
                                rotation_y_t_t_t,rotation_xz_t_t_t = rotation_info[iiii][0],rotation_info[iiii][1]
                                r_t = random.uniform(self.r_density_range[0],self.r_density_range[1]) * random.gauss(1,self.r_density_sigma)
                                r_t = r_t if r_t > 0 else 0
                                z_t_t_t,y_t_t_t = xy_get(r_t,random.uniform(0,360))
                                x_t_t_t,y_t_t_t,z_t_t_t = xyz_get_by_xyz(0,y_t_t_t,z_t_t_t,rotation_y_t_t_t,rotation_xz_t_t_t)

                                if isinstance(self.bcp_leave,str):
                                    self.obj.points_information.append([x_t + x_s + x_t_t + x_t_t_t + xyz_list_t_t_t[iiii][0],
                                                                        y_t + y_s + y_t_t + y_t_t_t + xyz_list_t_t_t[iiii][1],
                                                                        z_t + z_s + z_t_t + z_t_t_t + xyz_list_t_t_t[iiii][2],self.bcp_leave])
                                else:
                                    bcp_leave_t = proportion_choose(self.bcp_leave)
                                    self.obj.points_information.append([x_t + x_s + x_t_t + x_t_t_t + xyz_list_t_t_t[iiii][0],
                                                                        y_t + y_s + y_t_t + y_t_t_t + xyz_list_t_t_t[iiii][1],
                                                                        z_t + z_s + z_t_t + z_t_t_t + xyz_list_t_t_t[iiii][2],bcp_leave_t])

                        self.obj.obj_extend(obj_t_t, x=x_t + x_s + x_t_t, y=y_t + y_s + y_t_t, z=z_t + z_s + z_t_t)

                    self.obj.obj_extend(obj_t, x=x_t + x_s, y=y_t + y_s, z=z_t + z_s)

                self.obj.obj_extend(obj_brand_t,x=x_s,y=y_s,z=z_s)

            for p in self.obj.points_information:
                cmd_IO.CmdGenerator.create_block(self.x_centre + p[0],self.y_centre + p[1],self.z_centre + p[2],self.tick_now,p[3],self.cmd_builder)
                if self.out_put_air:
                    cmd_IO.CmdGenerator.create_block(self.x_centre + p[0], self.y_centre + p[1], self.z_centre + p[2],self.tick_now + 1000, "air", self.cmd_builder)
        self.tick_now += 1

    def xy_brand_obj_get(self,len_brand,rotation_xz):
        obj_t = Obj([])
        xyz_list = []
        x0,y0 = 0,0
        for i in range(int(len_brand * 2)):
            rotation_t = self.zy_func.tick(i / (len_brand * 2))
            r_t = self.r_sub_01_brand_func.tick(i / (len_brand * 2)) * random.uniform(self.r_sub_01_factor_deviation_range[0],self.r_sub_01_factor_deviation_range[1])
            obj_t.obj_extend(circle_get(r_t,2,self.main_brand).xy_rotate(rotation_t * 1),x=x0,y=y0)
            x_t,y_t = xy_get(0.5,rotation_t)
            x0 += x_t
            y0 += y_t
            xyz_list.append([x0,y0,0])
        obj_t.xz_rotate(rotation_xz)
        for i in range(len(xyz_list)):
            xyz_list[i][0],xyz_list[i][2] = xy_get_by_xy(xyz_list[i][0],xyz_list[i][2],rotation_xz)
        return obj_t,xyz_list

    def xy_brand_obj_02_get(self,len_brand,rotation_y,rotation_xz,rotation_xz_target=None):
        obj_t = Obj([])
        xyz_list = []
        x0,y0 = 0,0
        rotation_y_d = random.uniform(self.rotation_y_d_sub_02[0],self.rotation_y_d_sub_02[1])
        while True:
            rotation_y_t = random.uniform(self.rotation_zy_sub_02_range[0],self.rotation_zy_sub_02_range[1])
            rotation_xz_t = random.uniform(0,360)
            if (rotation_y_t > rotation_y + 15 or rotation_y_t < rotation_y - 15) and (rotation_xz_t > rotation_xz + 20 or rotation_xz_t < rotation_xz - 20)\
                    and (rotation_xz_t - 180 > rotation_xz + 20 or rotation_xz_t - 180 < rotation_xz - 20) and (rotation_xz_t + 180 > rotation_xz + 20 or rotation_xz_t + 180 < rotation_xz - 20):
                break

            # if (rotation_y_t > rotation_y + 15 or rotation_y_t < rotation_y - 15) and (rotation_xz_t - 100 < rotation_xz < rotation_xz_t + 100):
            #     break
        if rotation_xz_target is not None:
            rotation_xz_t = rotation_xz_target
        for i in range(int(len_brand * 2)):
            x_t,y_t = xy_get(0.5,rotation_y_t)
            x0 += x_t
            y0 += y_t
            xyz_list.append([x0,y0,0])
            r_t = self.r_sub_02_brand_func.tick(i / (len_brand * 2)) * random.uniform(
                self.r_sub_02_factor_deviation_range[0], self.r_sub_02_factor_deviation_range[1])
            obj_t.obj_extend(circle_get(r_t, 2, self.main_brand).xy_rotate(rotation_y_t), x=x0, y=y0)
            rotation_y_t += rotation_y_d * 0.5
        obj_t.xz_rotate(rotation_xz_t)
        for i in range(len(xyz_list)):
            xyz_list[i][0],xyz_list[i][2] = xy_get_by_xy(xyz_list[i][0],xyz_list[i][2],rotation_xz_t)
        return obj_t,xyz_list

    def xy_brand_obj_03_get(self,len_brand,rotation_y,rotation_xz):
        obj_t = Obj([])
        x0,y0 = 0,0
        xyz_list = []
        rotation_y_d = random.uniform(self.rotation_y_d_sub_03[0],self.rotation_y_d_sub_03[1])
        while True:
            rotation_y_t = random.uniform(self.rotation_zy_sub_03_range[0],self.rotation_zy_sub_03_range[1])
            rotation_xz_t = random.uniform(0,360)
            if (rotation_y_t > rotation_y + 15 or rotation_y_t < rotation_y - 15) and (rotation_xz_t - 60 < rotation_xz < rotation_xz_t + 60):
                break

        for i in range(int(len_brand * 2)):
            x_t,y_t = xy_get(0.5,rotation_y_t)
            x0 += x_t
            y0 += y_t
            xyz_list.append([x0,y0,0])
            r_t = self.r_sub_03_brand_func.tick(i / (len_brand * 2)) * random.uniform(
                self.r_sub_03_factor_deviation_range[0], self.r_sub_03_factor_deviation_range[1])
            r_t = r_t if r_t > 0.2 else 0.2
            if i % 2 == 0:
                obj_t.obj_extend(circle_get(r_t, 2, self.main_brand).xy_rotate(rotation_y_t), x=x0, y=y0)
            rotation_y_t += rotation_y_d * 0.5
        obj_t.xz_rotate(rotation_xz_t)
        for i in range(len(xyz_list)):
            xyz_list[i][0],xyz_list[i][2] = xy_get_by_xy(xyz_list[i][0],xyz_list[i][2],rotation_xz_t)
        return obj_t,xyz_list
