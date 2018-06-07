/* eslint no-param-reassign: 0 */
export default {
    incrementCounter(state) {
        state.counter++;
    },
    decrementCounter(state) {
        state.counter--;
    },
    setLayout(state, layout) {
        // console.log(state.layouts, state.layout, state.layouts.includes(layout));
        // if (state.layouts.includes(layout))
        //     state.layout = layout;
        // state.layout = state.defaultLayout;
    },
};
