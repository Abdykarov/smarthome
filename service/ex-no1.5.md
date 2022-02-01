#Model and Dto
###Exposing the model for simplicity or clear separation of layers with DTOs
My opinion on this - always separate layers with DTO, because if you need to hide some data to the outside world, without dto it will be impossible, 
it will destroy database scheme. In case of using dto, you just change some attributes in dto class and that's all, model stays the same.