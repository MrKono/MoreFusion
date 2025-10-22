## How to add unique FusionReactors
(_See [example.js](https://github.com/MrKono/MoreFusion/blob/master/kubejs/startup_scripts/example.js) for the full script._)
### Code Explanation
To register a unique FusionReactor, use the following unique function:
``` js
event.create(name, 'multiblock').machine((holder) => new CustomFusionReactorMachine(holder, tier, tierName, baseHeat))
```
- tier (_int_) — Specifies the machine’s tier.
- tierName (_String_) — The name displayed in the JEI or REI.
- baseHeat (_long_) — The amount of heat per EnergyHatch.  
  To set the total amount of EnergyHatch to a value other than 16, please do the following:
``` js
event.create(name, 'multiblock').machine((holder) => new CustomFusionReactorMachine(holder, tier, ierName, baseHeat, energyHatchAmount))
```
- energyHatchAmount (_int_): Total amount of EnergyHatches.  
---
```js
.recipeTypes('fusion_reactor_mf')
```
- When `'fusion_reactor_mf'` is set as recipeTypes, up to 6 inputs/outputs can be used for item/fluid.
---
If you want to restrict the tier of EnergyHatch that can be used
```js
Predicates.blocks(CustomFusionReactorMachine.rangedEnergyHatch(tier))
```
- tier (_int_): Only `tier` or higher of EnergyHatches  are available.
```js
Predicates.blocks(CustomFusionReactorMachine.rangedEnergyHatch(start, end))
```
- The EnergyHatch is only available between `start` and `end` tier.

## Tooltip
(_See [tooltip.js](https://github.com/MrKono/MoreFusion/blob/master/kubejs/client_scripts/tooltip.js) for the full script._)
```js
MoreFusionUtil.formatWithSIPrefix(value)
```
- Returns a positive long value in the format x.xx to xxx with a prefix.
  - example: `MoreFusionUtil.formatWithSIPrefix(1000000)` -> 1.00M