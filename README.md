<!--
parent:
  order: false
-->

<div align="center">
  <h1>FunctionX ($FX) - FXCore REST API library for Java</h1>
</div>
<p align="center">
  <img src="https://github.com/FrenchXCore/JavaFxCoreRestAPI/blob/main/resources/logo-functionx-730x482.jpeg" />
</p>

## Introduction

FunctionX is a decentralized ecosystem relying on the Cosmos ecosystem.

FXCore is the main blockchain ($FX) upon which is relying the PundiX ($PUNDIX) on-chain payment system.

This Java API allows you to interface to a FunctionX mainnet/testnet node in order to use its RPC REST interface (usually on port 1317).

## Quick start

<p>First, you need to make sure the node's API is enabled.</p>
<p>In order to do that, go into the node's configuration directory (usually ~/.fxcore/config), and modify the 'app.toml' file in the [app] section so that enable=true, and restart your node (`sudo systemctl restart fxcored`).</p>
<br/>
<p>Have a look at the 'Example.java' class to understand how to use it.</p>
<p>The RestService class includes all the methods available for query on any Cosmos-based RPC interface.</p>

## Useful to know

Some latest modules of the Cosmos SDK are not yet implemented into the FXCore mainnet or testnet nodes (authz, nft, etc.) but methods are already provided.
When used, they might return an error ("not implemented").

## To be achieved

Implementation of error messages.

## Testing

This library is not yet fully tested. So please be indulgent.

The following methods were tested successfully.

### Module 'bank'
- bankDenomOwners(...) -> NOT IMPLEMENTED

### Module 'base.tendermint'
+ baseTendermintNodeInfo(...)
+ baseTendermintSyncing(...)
+ baseTendermintLatestBlock(...)

### Module 'distribution'
+ distributionValidatorCommission(...)

### Module 'slashing'
+ slashingParams()
+ slashingSigningInfos(...)

### Module 'staking'
+ stakingPool(...)
+ stakingValidators(...)
+ stakingValidatorDelegations(...)

## Updates
- v0.1.0 :   initial draft version
- v0.2.0 :   refactoring of the packages - addition of the tendermint module - fix : dates
- v0.3.0 :   addition of the following modules: authz, evidence, feegrant, group, tx, upgrade
- v0.4.0 :   fixed : durations, QueryPoolResponse, QueryValidatorsResponse, pagination, tx
             added : 'ibc' and 'other' modules, transaction decoding, QueryGasPriceResponse
- v0.5.0 :   added real message deserialization, and default behaviour for unknown messages
- v0.5.1-5 : added all proposal types deserialization, fix govProposals, improve javaDoc (RestService),
             fix cosmossdk.types.gov.v1beta1 & v1beta2.Proposal (withdrawEnableAddr to Boolean)

Have fun !
