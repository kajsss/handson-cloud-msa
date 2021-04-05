<template>
    <div class="py-5">
        <b-container class="bg-light py-3 px-lg-5">
            <b-row class="mt-5 py-2">
                <h3>My Orders</h3>
            </b-row>
            <b-row>
                <div>
                    <b-table striped hover :items="orders" :fields="fields">
                        <template #cell(id)="row">
                            <b-button size="sm" class="mr-2" @click="onClickOrderId(row.item.id)">
                                {{row.item.id}}
                            </b-button>
                        </template>

                        <template #cell(cancel)="row">
                            <b-button size="sm" class="mr-2" @click="onClickCancelOrderItem(row)">
                                Cancel
                            </b-button>
                        </template>
                    </b-table>
                </div>
            </b-row>
            <hr/>
            <strong v-if="selectedOrder">
                <b-row class="mt-3 py-2">
                    <h3>Order Detail </h3>
                </b-row>
              <b-row class="mt-1 py-3">
                <h3>Id : {{selectedOrder.id}}</h3>
              </b-row>
                <b-table striped hover :items="selectedOrder.orderProducts" :fields="detailFields">
                </b-table>
            </strong>
        </b-container>
    </div>
</template>

<script>
    import {mapActions, mapGetters} from 'vuex'

    export default {
        name: 'OrderList',
        data: () => {
            return {
                fields: ['id', 'productCount', 'emailAddress', 'address', 'shippingStatus', 'cancel'],
                detailFields: ['productId','productName','quantity'],
                products: [],
                selectedOrder: null,
            }
        },
        components: {},
        methods: {
            ...mapActions('order', ['deleteOrders']),
            async onClickCancelOrderItem(row) {
                const targetId = row.item.id;
                await this.deleteOrders(targetId);
                if(this.selectedOrder.id === targetId) {
                    this.selectedOrder = null;
                }
            },
            onClickOrderId(id) {
                this.selectedOrder = this.orders.find(order => order.id === id);
            }
        },
        computed: mapGetters({
            orders: 'order/orders'
        }),

        mounted() {

        },
        watch: {

        }
    }
</script>
