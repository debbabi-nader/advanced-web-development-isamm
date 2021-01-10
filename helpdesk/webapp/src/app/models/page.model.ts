export interface IPage<D> {

  elements: Array<D>;
  index: number;
  size: number;
  first: boolean;
  last: boolean;
  totalPages: number;
  totalElements: number;

}
