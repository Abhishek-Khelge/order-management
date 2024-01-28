package com.order.management.swagger;

public class SwaggerExample {


    public static final String INTERNAL_SERVER_ERROR_RESPONSE = """
            {
                "response_status": {
                    "status_code": 500,
                    "messages": [
                        {
                            "message": "Internal error",
                            "code": "50003",
                            "type": "failure"
                        }
                    ],
                    "status": "failed"
                }
            }
            """;

    public static final String ADD_PRODUCT_REQUEST = """
            {
                "serial_number": "3746GTW21Y723",
                "product_name": "Wall Lamp D",
                "price": 343,
                "quantity": 2,
                "description": "Wall Lamp with of light 200L"
            }
            """;
    public static final String PRODUCT_DETAILS_RESPONSE = """
            {
                "response_status": {
                    "status_code": 201,
                    "messages": [
                        {
                            "code": "2000",
                            "type": "success"
                        }
                    ],
                    "status": "success"
                },
                "inventory_product": {
                    "serial_number": "3746GTW21Y723",
                    "product_name": "Wall Lamp D",
                    "quantity": 2
                }
            }
            """;

    public static final String PRODUCT_DETAILS_ALREADY_SN_RESPONSE = """
            {
                "response_status": {
                    "status_code": 400,
                    "messages": [
                        {
                            "field": "product already exits for for given serial number 3746GTW21Y723, the serial number should be unique, try updating the product details",
                            "message": "failure",
                            "code": "40203",
                            "args": [
                                "3746GTW21Y723"
                            ]
                        }
                    ],
                    "status": "failed"
                }
            }
            """;

    public static final String UPDATE_PRODUCT_REQUEST = """
            {
                "product_name": "Wall Lamp D",
                "increase_quantity_by": 2,
                "description": "Wall Lamp with of light 300L"
            }
            """;


    public static final String UPDATE_PRODUCT_RESPONSE = """
            {
                "response_status": {
                    "status_code": 201,
                    "messages": [
                        {
                            "code": "2000",
                            "type": "success"
                        }
                    ],
                    "status": "success"
                },
                "inventory_product": {
                    "serial_number": "3746GTW21Y723",
                    "product_name": "Wall Lamp D",
                    "price": 343.00,
                    "quantity": 4,
                    "description": "Wall Lamp with of light 300L"
                }
            }
            """;

    public static final String INVALID_INPUT_UPDATE_PRODUCT_REQUEST = """
            {
                "response_status": {
                    "status_code": 400,
                    "messages": [
                        {
                            "field": "quantity",
                            "message": "product quantity can't be increased and decreased at the same time ",
                            "code": "failure",
                            "type": "4001"
                        }
                    ],
                    "status": "failed"
                }
            }
            """;

    public static final String INVALID_INPUT_SN_UPDATE_PRODUCT_REQUEST = """
            {
                "response_status": {
                    "status_code": 400,
                    "messages": [
                        {
                            "field": "product does not exist in inventory for the given serial number 3746GTW21Y724",
                            "message": "failure",
                            "code": "40201",
                            "args": [
                                "3746GTW21Y724"
                            ]
                        }
                    ],
                    "status": "failed"
                }
            }
            """;

    public static final String DELETE_PRODUCT_RESPONSE = """
            {
                "response_status": {
                    "status_code": 200,
                    "messages": [
                        {
                            "code": "2000",
                            "type": "success"
                        }
                    ],
                    "status": "success"
                },
                "inventory_removed": {
                    "serial_number": "3746GTW21Y7242",
                    "product_name": "Wall Lamp C",
                    "price": 343.00,
                    "quantity": 2,
                    "description": "Wall Lamp with of light 200L"
                }
            }
            """;

    public static final String DELETE_NOT_THERE_PRODUCT_RESPONSE = """
            {
                "response_status": {
                    "status_code": 400,
                    "messages": [
                        {
                            "field": "product does not exist in inventory for the given serial number 3746GTW21Y7242",
                            "message": "failure",
                            "code": "40201",
                            "args": [
                                "3746GTW21Y7242"
                            ]
                        }
                    ],
                    "status": "failed"
                }
            }
            """;

    public static final String GET_BY_SN_RESPONSE = """
            {
                "response_status": {
                    "status_code": 200,
                    "messages": [
                        {
                            "code": "2000",
                            "type": "success"
                        }
                    ],
                    "status": "success"
                },
                "products_details": {
                    "product_name": "Wall Lamp A",
                    "price": 231.00,
                    "description": "Wall Lamp with, of light 3ft"
                }
            }
            """;
    public static final String GET_BY_SN_NOT_EXIST_RESPONSE = """
            {
                "response_status": {
                    "status_code": 400,
                    "messages": [
                        {
                            "field": "product does not exist in inventory for the given serial number serialNumber",
                            "message": "failure",
                            "code": "40201",
                            "args": [
                                "serialNumber",
                                "3746GTW21Y72398"
                            ]
                        }
                    ],
                    "status": "failed"
                }
            }
            """;

    public static final String GET_PRODUCT_DETAILS_RESPONSE = """
            {
                "response_status": {
                    "status_code": 200,
                    "messages": [
                        {
                            "code": "2000",
                            "type": "success"
                        }
                    ],
                    "status": "success",
                    "list_info": {
                        "page": 0,
                        "total_count": 3,
                        "page_count": 2,
                        "page_size": 2,
                        "sort_field": "createdAt",
                        "sort_order": "ASC"
                    }
                },
                "products_details": [
                    {
                        "product_name": "Table Lamp",
                        "price": 450.34,
                        "description": "Table Lamp with 3 legs, of light 3ft"
                    },
                    {
                        "product_name": "Wall Lamp A",
                        "price": 231.00,
                        "description": "Wall Lamp with, of light 3ft"
                    }
                ]
            }
            """;
}
