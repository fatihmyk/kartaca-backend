import {ApiService} from "../api.service";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Rx";
import {map} from "rxjs/internal/operators";


@Injectable()
export class UserService{

  private USER_PATH = "/users";

  constructor(private apiService: ApiService) {
  }

  getAll(): Observable<any> {
    return this.apiService.get(this.USER_PATH).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          console.log(res);
          return {};
        }
      }
    ));
  }

  getById(id): Observable<any> {
    return this.apiService.get(this.USER_PATH,id).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          console.log(res);
          return {};
        }
      }
    ));
  }

  createUser(user) : Observable<any> {
    return this.apiService.post(this.USER_PATH,user).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          console.log(res);
          return {};
        }
      }
    ));
  }

 /* delete(id): Observable<any> {
    return this.apiService.delete(this.USER_PATH+'/'+id).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          console.log(res);
          return {};
        }
      }
    ));
  }*/


}
