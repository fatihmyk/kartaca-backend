import {ApiService} from "../api.service";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Rx";
import {map} from "rxjs/internal/operators";


@Injectable()
export class TravelService{

  private PROJECT_PATH = "/travel";

  constructor(private apiService: ApiService) {
  }

  getAll(page): Observable<any> {
    return this.apiService.get(this.PROJECT_PATH+'/pagination',page).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          return {};
        }
      }
    ));
  }

  getAllNP(): Observable<any> {
    return this.apiService.get(this.PROJECT_PATH).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          return {};
        }
      }
    ));
  }

  getById(id): Observable<any> {
    return this.apiService.get(this.PROJECT_PATH,id).pipe(map(
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

  createTravel(travel) : Observable<any> {
    return this.apiService.post(this.PROJECT_PATH,travel).pipe(map(
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

  delete(id): Observable<any> {
    return this.apiService.delete(this.PROJECT_PATH+'/'+id).pipe(map(
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

}
