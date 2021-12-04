
import 'package:json_annotation/json_annotation.dart';


@JsonSerializable()
class User {
  String name;
  String email;

  User(this.name,this.email);

  // //不同的类使用不同的mixin即可
  // factory User.fromJson(Map<String, dynamic> json) => _$UserFromJson(json);
  // Map<String, dynamic> toJson() => _$UserToJson(this);
}
