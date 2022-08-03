package com.example.springbootmanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

/**
 * <p>
 * 
 * </p>
 *
 * @author luzber
 * @since 2022-08-01
 */
@TableName("sys_user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("用户名	")
      private String username;

      @ApiModelProperty("密码")
      private String password;

      @ApiModelProperty("昵称")
      private String nickname;

      @ApiModelProperty("邮箱")
      private String email;

      @ApiModelProperty("电话")
      private Integer phone;

      @ApiModelProperty("地址")
      private String address;

      @ApiModelProperty(" 创建时间")
      private LocalDateTime createTime;

    private String avatarUrl;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    
    public String getUsername() {
        return username;
    }

      public void setUsername(String username) {
          this.username = username;
      }
    
    public String getPassword() {
        return password;
    }

      public void setPassword(String password) {
          this.password = password;
      }
    
    public String getNickname() {
        return nickname;
    }

      public void setNickname(String nickname) {
          this.nickname = nickname;
      }
    
    public String getEmail() {
        return email;
    }

      public void setEmail(String email) {
          this.email = email;
      }
    
    public Integer getPhone() {
        return phone;
    }

      public void setPhone(Integer phone) {
          this.phone = phone;
      }
    
    public String getAddress() {
        return address;
    }

      public void setAddress(String address) {
          this.address = address;
      }
    
    public LocalDateTime getCreateTime() {
        return createTime;
    }

      public void setCreateTime(LocalDateTime createTime) {
          this.createTime = createTime;
      }
    
    public String getAvatarUrl() {
        return avatarUrl;
    }

      public void setAvatarUrl(String avatarUrl) {
          this.avatarUrl = avatarUrl;
      }
    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }

    @Override
    public String toString() {
        return "User{" +
              "username=" + username +
                  ", password=" + password +
                  ", nickname=" + nickname +
                  ", email=" + email +
                  ", phone=" + phone +
                  ", address=" + address +
                  ", createTime=" + createTime +
                  ", avatarUrl=" + avatarUrl +
                  ", id=" + id +
              "}";
    }
}
